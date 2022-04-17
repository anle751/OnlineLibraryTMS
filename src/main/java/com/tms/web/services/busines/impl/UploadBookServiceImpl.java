package com.tms.web.services.busines.impl;

import com.kursx.parser.fb2.FictionBook;
import com.tms.web.entities.library.Book;
import com.tms.web.services.busines.BookComplexService;
import com.tms.web.services.busines.ParseFB2BookService;
import com.tms.web.services.busines.UploadBookService;
import com.tms.web.services.validators.Validator;
import com.tms.web.services.validators.impl.FileValidatorImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
@Log4j2
public class UploadBookServiceImpl implements UploadBookService {
    @Autowired
    private ParseFB2BookService parseFB2BookService;
    @Autowired
    private BookComplexService bookComplexService;
    @Autowired
    private FileValidatorImpl fileValidator;
    @Autowired
    private Validator<MultipartFile> multipartFileValidator;

    @Override
    public Book upload(MultipartFile multipartFile) throws IOException {
        Book book = null;
        if (multipartFileValidator.validate(multipartFile)){
            if (multipartFile != null) {
                log.debug("File " + multipartFile.getName() + " has " + multipartFile.getBytes().length + " bytes");
                long threadId = Thread.currentThread().getId();
                String threadName = Thread.currentThread().getName();
//            ClassLoader classLoader = getClass().getClassLoader();
//            File bookCreated = new File(classLoader.getResource(".").getFile() + "/bookCreated" + id + ".tmp");
                File bookCreated = null;
                try {
                    bookCreated = File.createTempFile("tmpBook", threadName + "_" + String.valueOf(threadId));
                    if (bookCreated.exists()) {
                        log.debug("temporary book file created in: " + bookCreated);
                        multipartFile.transferTo(bookCreated);
                        book = parseAndSaveBookToDB(bookCreated);
                        if (bookCreated.delete()) {
                            log.debug("temporary book file deleted:" + bookCreated);
                        }
                    }
                } catch (RuntimeException re) {
                    log.fatal("Runtime exception:"+re.getMessage());
                } finally {
                    if (Objects.nonNull(bookCreated)) {
                        if (bookCreated.exists()) {
                            if (bookCreated.delete()) {
                                log.debug("temporary book file deleted");
                            }else {
                                log.fatal("temporary book file don't deleted");
                            }
                        }
                    }
                }


//            if (bookCreated.createNewFile()) {
//                System.out.println("temporary book file created");
//                multipartFile.transferTo(bookCreated);
//                book = parseAndSaveBookToDB(bookCreated);
//                if (bookCreated.delete()) {
//                    System.out.println("temporary book file deleted");
//                }
//            }
//            boolean delete = bookCreated.delete();
//            if (delete) {
//                System.out.println("Tmp book file deleted");
//            }

            }
        }

        return book;
    }

    @Override
    public Book upload(File file) throws IOException {
        Book book = null;
        try {
            if (fileValidator.validate(file)) {
                book = parseAndSaveBookToDB(file);
            }
        }catch (RuntimeException re){
            log.fatal("Runtime exception:"+re.getMessage());
        }
        return book;
    }

    private Book parseAndSaveBookToDB(File file) throws IOException {
        Book book = null;
        try {
            FictionBook fb2 = new FictionBook(file);
            book = parseFB2BookService.parse(fb2);
            if (Objects.nonNull(book)) {
                book = bookComplexService.save(book);
            }else {
                log.warn("Fail parse fb2 book to Book.class");
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            log.fatal(e.getMessage());
        }
        return book;
    }
}
