package com.tms.web.services.populators;

import com.tms.web.entities.repositories.*;
import com.tms.web.entities.security.ROLE;
import com.tms.web.entities.security.User.User;
import com.tms.web.services.busines.impl.UploadBookServiceImpl;
import com.tms.web.services.entities.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.io.*;
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
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    ServletContext context;
    @Autowired
    ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        userPopulator();
        try {
            bookPopulator();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void bookPopulator() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        String realPath = context.getRealPath("resources/populatorData");
        System.out.println(realPath);
        Resource[] resources = applicationContext.getResources("classpath:/populatorData/*.fb2");


        System.out.println(Arrays.toString(resources));
        for (int i=0;i<resources.length;i++){
            File file = File.createTempFile("tmp", String.valueOf(i));
            System.out.println("File:"+file);
            InputStream inputStream = resources[i].getInputStream();
            long l = inputStream.transferTo(new FileOutputStream(file));
            System.out.println("l: "+l);
            boolean exists = file.exists();
            System.out.println("File exist?: "+file.exists());
            if (exists) {
                uploadBookService.upload(file);
            }
            boolean delete = file.delete();
            System.out.println("file deleted?: "+delete);
        }


//        File file = new File(realPath);
//        System.out.println(Arrays.toString(file.listFiles()));


//        String file = classLoader.getResource("classpath:populatorData").getFile();
//        System.out.println(file);
//        File cat = new File(file+"populatorData");
//        System.out.println("data"+cat);
//        List<File> f = Arrays.asList(Objects.requireNonNull(cat.listFiles()));
//        System.out.println("files:"+f);

//        File file = resource.getFile();
//        System.out.println(file);
//        File[] files1 = file.listFiles();
//        System.out.println(files1);

//        File catalog = new File(Objects.requireNonNull(classLoader.getResource(".")).getFile()+"static"+"/populatorData");
//        List<File> files = Arrays.asList(Objects.requireNonNull(catalog.listFiles()));
//        System.out.println(files);
//        files.forEach(x-> {
//            try {
//                uploadBookService.upload(x);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }
}
