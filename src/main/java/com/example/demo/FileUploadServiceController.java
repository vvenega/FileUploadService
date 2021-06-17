package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class FileUploadServiceController {
	
	//private static final String VALID_CUSTOMER = "http://localhost:4200";
	private static final String VALID_CUSTOMER = "*";
	private static final String PATH="/users/victorvenegas/desktop/testupload/";
	
    Map<String, MultipartFile> filemap=new HashMap<String, MultipartFile>();
    
    @Autowired
	private ListingDaoProxy proxy;
    
    @Autowired
    private FileUploadDaoProxy fileProxy;
    
    @CrossOrigin(origins = VALID_CUSTOMER)
    @PostMapping("/uploadfile")
    public ResponseEntity<String> handlefileupload(@RequestParam("selectedfiles") MultipartFile[] multifile,
    		@RequestParam("username")String username){
        String message="";
        try {
            message="succesfull";
            for(int i=0;i<multifile.length;i++){
            System.err.println(multifile[i].getOriginalFilename());
            String filename=multifile[i].getOriginalFilename();
            byte[] file = multifile[i].getBytes();
            
            if(filename!=null && (filename.endsWith("xlsx") || filename.endsWith("xls"))) {
            	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
                 LocalDateTime now = LocalDateTime.now();
                 String date =dtf.format(now);
                 int index = filename.indexOf('.');
         	    String newname=username;
         	    String ext = filename.substring(index);
         	    newname = newname+"_"+date+ext;
            try (FileOutputStream fos = new FileOutputStream(PATH+newname)) {
                fos.write(file);
                //fos.close // no need, try-with-resources auto close
                fileProxy.setFileLoadRecord(username, newname, "output", "procesando");
                String outputfile = proxy.postExcelDao(username,newname);
                fileProxy.updateFileLoadRecord(username, newname,"Termino.",outputfile);
            }catch(Exception e) {
            	e.printStackTrace();
            }
            }
            
             }
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            e.printStackTrace();
            message="failed";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
    
    @CrossOrigin(origins = VALID_CUSTOMER)
    @GetMapping("/getFileLoads/{username}")
    public List<FileLoadBean> getFileLoads(@PathVariable String username){
    	List<FileLoadBean> lst = new ArrayList<FileLoadBean>();
    	
    	try {
    		lst = fileProxy.getFileLoads(username);
    	}catch(Exception e) {
    		e.printStackTrace();
    		lst =new ArrayList<FileLoadBean>();
    	}
    	
    	return lst;
    }
    
    @CrossOrigin(origins = VALID_CUSTOMER)
    @GetMapping("/fileDownload/{username}/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String username,@PathVariable String filename){
    	
    	
    	try {
    		System.err.println(PATH+filename);
    		File file = new File(PATH+filename);
    		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

    		HttpHeaders headers= new HttpHeaders();
    	    headers.add("Content-Disposition", "attachment; filename =file.xlsx");
    	    //headers.add("Content-Type","xlsx");
    	    return ResponseEntity.ok()
    	            .headers(headers)
    	            .contentLength(file.length())
    	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
    	            .body(resource);
    	}catch(Exception e) {
    		e.printStackTrace();
    		
    	}
    	
    	return null;
    }
    
    
    

}
