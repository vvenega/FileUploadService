package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FileUploadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadServiceApplication.class, args);
	}

}
