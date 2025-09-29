package org.example.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CafeApplication {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
        SpringApplication.run(CafeApplication.class, args);
    }

}
