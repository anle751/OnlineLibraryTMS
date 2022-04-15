package com.tms.web.services.busines;

import com.tms.web.entities.library.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public interface UploadBookService {
    public Book upload(MultipartFile multipartFile) throws IOException;
    public Book upload(File file) throws IOException;
}
