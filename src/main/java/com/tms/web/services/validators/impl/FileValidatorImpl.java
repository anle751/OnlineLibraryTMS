package com.tms.web.services.validators.impl;

import com.tms.web.services.validators.Validator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Log4j2
@Service
public class FileValidatorImpl implements Validator<File> {
    @Override
    public boolean validate(File file) {
        if (Objects.nonNull(file)) {

            if (file.exists()) {
                if (file.isFile()) {
                    if (file.canRead()) {
                        return true;
                    } else {
                        log.warn("File cannot be read:" + file.getAbsolutePath());
                    }
                } else {
                    log.warn("Not correct file path");
                }

            } else {
                log.warn("File not found");
            }
        } else {
            log.warn("File is null");
        }
        return false;
    }
}
