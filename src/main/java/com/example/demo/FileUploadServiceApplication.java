package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class FileUploadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadServiceApplication.class, args);
	}
	
	@Bean
	  @LoadBalanced
	  public RestTemplate restTemplate() {
	    //return new RestTemplate();
		
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

      factory.setConnectTimeout(300000);
      factory.setReadTimeout(300000);

      return new RestTemplate(factory);
	  }

}
