package com.tms.web.services.validators.impl;

import com.tms.web.exceptions.UploadFileException;
import com.tms.web.services.validators.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
@Service
@Log4j2
public class MultipartFileValidatorImpl implements Validator<MultipartFile> {
    @Override
    public boolean validate(MultipartFile obj) {
        if (Objects.isNull(obj)) {
            log.warn("multipart file is null");
            throw new UploadFileException("multipart file is null");
        }
        if (obj.isEmpty()){
            log.warn("multipart file is empty");
            throw new UploadFileException("multipart file is empty");
        }
        return true;
    }
}
