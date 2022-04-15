package com.tms.web.services.busines.impl;

import com.kursx.parser.fb2.FictionBook;
import com.tms.web.entities.library.Book;
import com.tms.web.services.busines.BookComplexService;
import com.tms.web.services.busines.ParseFB2BookService;
import com.tms.web.services.busines.UploadBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class UploadBookServiceImpl implements UploadBookService {
    @Autowired
    private ParseFB2BookService parseFB2BookService;
    @Autowired
    private BookComplexService bookComplexService;

    @Override
    public Book upload(MultipartFile multipartFile) throws IOException {
        Book book = null;
        if (multipartFile != null) {
            String name = multipartFile.getName();
            System.out.println("File " + name + " has " + multipartFile.getBytes().length + " bytes");
            long id = Thread.currentThread().getId();
            ClassLoader classLoader = getClass().getClassLoader();
            File bookCreated = new File(classLoader.getResource(".").getFile() + "/bookCreated" + id + ".tmp");
            if (bookCreated.createNewFile()) {
                System.out.println("temporary book file created");
                multipartFile.transferTo(bookCreated);
                book = parseAndSaveBookToDB(bookCreated);
                if (bookCreated.delete()) {
                    System.out.println("temporary book file deleted");
                }
            }
        }
        return book;
    }

    @Override
    public Book upload(File file) throws IOException {
        Book book = null;
        book = parseAndSaveBookToDB(file);
        return book;
    }

    private Book parseAndSaveBookToDB(File file) throws IOException {
        Book book = null;
        try {
            FictionBook fb2 = new FictionBook(file);
            book = parseFB2BookService.parse(fb2);
            if (Objects.nonNull(book)) {
                book = bookComplexService.save(book);
                System.out.println();
                if (Objects.isNull(book)) {
                    throw new IOException("Book non saved");//заменить на свою ошибку!!
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new IOException("Parsing file not completed");
        }
        return book;
    }
}
