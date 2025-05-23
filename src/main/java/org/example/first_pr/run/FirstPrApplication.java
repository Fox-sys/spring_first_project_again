package org.example.first_pr.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.example.first_pr")
@EntityScan(basePackages = "org.example.first_pr.adapters.db.tables")
@EnableJpaRepositories(basePackages = "org.example.first_pr.adapters.db.jpa_repos")
public class FirstPrApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstPrApplication.class, args);
    }

}
