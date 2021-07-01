package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static Properties properties;
	
	   static {
		   properties= new Properties();
		   readProperties("/msconf/config.properties");
		   //readProperties("/users/victorvenegas/config/fileuploadservice/config.properties");
	   }

		
		public static void readProperties(String file){
		    FileInputStream input = null;
		    try {
		      input = new FileInputStream(new File(file));
		      properties.load(input);

		    } catch (FileNotFoundException e) {
		      // TODO Auto-generated catch block
		      System.err.println(e.getMessage());
		    } catch (IOException e) {
		      // TODO Auto-generated catch block
		      System.err.println(e.getMessage());
		    }finally {
		    	try {
		    	if(input!=null)
		    		input.close();
		    	}catch(Exception e) {
		    		System.err.println(" Error Closing File properties Login Service"+e.getLocalizedMessage());
		    	}
		    }
			

		}
		
		public static String getProperty(String property) {
			return (String)properties.get(property);
		}

}
