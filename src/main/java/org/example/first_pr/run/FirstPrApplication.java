package org.example.first_pr.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "org.example.first_pr")
@EntityScan(basePackages = "org.example.first_pr.adapters.db.tables")
public class FirstPrApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstPrApplication.class, args);
    }

}
