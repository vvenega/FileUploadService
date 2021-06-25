package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

//@FeignClient(name="FileUploadDAO",url="http://localhost:8105/")
public class FileUploadDaoProxy {
	
	private RestTemplate restTemplate;
	
	private final String FILE_DAO="FILE_DAO";
	
	//@GetMapping("/FileLoadSetFileRecord/{username}/{namefile}/{outputfile}/{status}")
	public boolean setFileLoadRecord(@PathVariable String username,@PathVariable String namefile,@PathVariable String outputfile,@PathVariable String status) {
		boolean response=false;
		
		try {
		response=restTemplate.exchange(Config.getProperty(this.FILE_DAO)+"/FileLoadSetFileRecord/{username}/{namefile}/{outputfile}/{status}",
		          HttpMethod.GET, null, new ParameterizedTypeReference<Boolean>() {}, username,namefile,outputfile,status).getBody();
		}catch(Exception e) {
			System.err.println(e.getMessage());
			response=false;
		}
		
		return response;
	}
	
	//@GetMapping("/FileLoadUpdateFileRecord/{username}/{namefile}/{status}/{outputfile}")
	public boolean updateFileLoadRecord(@PathVariable String username,@PathVariable String namefile,@PathVariable String status,@PathVariable String outputfile) {
		
      boolean response=false;
		
		try {
		response=restTemplate.exchange(Config.getProperty(this.FILE_DAO)+"/FileLoadUpdateFileRecord/{username}/{namefile}/{status}/{outputfile}",
		          HttpMethod.GET, null, new ParameterizedTypeReference<Boolean>() {}, username,namefile,status,outputfile).getBody();
		}catch(Exception e) {
			System.err.println(e.getMessage());
			response=false;
		}
		
		return response;
		
	}
	
	//@GetMapping("/GetFileLoadsDao/{username}")
	public List<FileLoadBean> getFileLoads(@PathVariable String username){
		
		
		List<FileLoadBean> response=null;
		
		try {
		response=restTemplate.exchange(Config.getProperty(this.FILE_DAO)+"/GetFileLoadsDao/{username}",
		          HttpMethod.GET, null, new ParameterizedTypeReference<List<FileLoadBean>>() {}, username).getBody();
		}catch(Exception e) {
			System.err.println(e.getMessage());
			response=new ArrayList<FileLoadBean>();
		}
		
		return response;
		
	}
	
	public FileUploadDaoProxy setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate=restTemplate;
		return this;
	}

}
