package com.erp.management;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.erp.management.repository.UserRepository;
import com.erp.management.entity.User;

@SpringBootApplication
public class ErpManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErpManagementSystemApplication.class, args);
    }   

}
