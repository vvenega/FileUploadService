package com.example.demo;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="FileUploadDAO",url="http://localhost:8105/")
public interface FileUploadDaoProxy {
	
	@GetMapping("/FileLoadSetFileRecord/{username}/{namefile}/{outputfile}/{status}")
	public boolean setFileLoadRecord(@PathVariable String username,@PathVariable String namefile,@PathVariable String outputfile,@PathVariable String status);
	
	@GetMapping("/FileLoadUpdateFileRecord/{username}/{namefile}/{status}/{outputfile}")
	public boolean updateFileLoadRecord(@PathVariable String username,@PathVariable String namefile,@PathVariable String status,@PathVariable String outputfile);
	
	@GetMapping("/GetFileLoadsDao/{username}")
	public List<FileLoadBean> getFileLoads(@PathVariable String username);

}
