package org.example.first_pr.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.example.first_pr")
public class FirstPrApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstPrApplication.class, args);
    }

}
