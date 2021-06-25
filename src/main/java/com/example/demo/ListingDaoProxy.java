package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

//@FeignClient(name="ListingsDAO",url="http://localhost:8001/")
public class ListingDaoProxy {
	
	private RestTemplate restTemplate;
	
	private final String DAO="DAO";
	
	//@GetMapping("/ListingPostExcelDao/{username}/{namefile}")
	public String postExcelDao(@PathVariable String username,@PathVariable String namefile) {
		
		String response =null;
		
		try {
			
			response=restTemplate.exchange(Config.getProperty(this.DAO)+"/ListingPostExcelDao/{username}/{namefile}",
			          HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, username,namefile).getBody();
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
			response=null;
		}
		
		return response;
		
	}
	
	
	public ListingDaoProxy setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate=restTemplate;
		return this;
	}

}
