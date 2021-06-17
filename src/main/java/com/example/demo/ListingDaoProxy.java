package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ListingsDAO",url="http://localhost:8001/")
public interface ListingDaoProxy {
	
	@GetMapping("/ListingPostExcelDao/{username}/{namefile}")
	public String postExcelDao(@PathVariable String username,@PathVariable String namefile);

}
