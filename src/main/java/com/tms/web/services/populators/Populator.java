package com.tms.web.services.populators;

import com.tms.web.entities.repositories.*;
import com.tms.web.entities.security.ROLE;
import com.tms.web.entities.security.User.User;
import com.tms.web.services.busines.impl.UploadBookServiceImpl;
import com.tms.web.services.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class Populator {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private UserService userService;
    @Autowired
    UploadBookServiceImpl uploadBookService;

    @PostConstruct
    public void init() {
        userPopulator();
        bookPopulator();
    }

    private void userPopulator(){
        User user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles(Arrays.asList(ROLE.ROLE_USER, ROLE.ROLE_ADMIN))
                .nickName("Vasya")
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .build();
        userService.save(user1);
    }

    private void bookPopulator(){
        ClassLoader classLoader = getClass().getClassLoader();
        File catalog = new File(Objects.requireNonNull(classLoader.getResource(".")).getFile()+"/populatorData");
        List<File> files = Arrays.asList(Objects.requireNonNull(catalog.listFiles()));
        files.forEach(x-> {
            try {
                uploadBookService.upload(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
