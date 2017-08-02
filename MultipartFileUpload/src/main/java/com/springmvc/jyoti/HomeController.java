package com.springmvc.jyoti;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springmvc.jyoti.business.ImageDataBusiness;
import com.springmvc.jyoti.model.ImageData;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private ImageDataBusiness imageDataBusiness;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "home";
	}
	
	@Autowired(required=true)
	@Qualifier(value="imageDataBusiness")
	public void setImageDataBusiness(ImageDataBusiness imageDataBusiness) {
		this.imageDataBusiness = imageDataBusiness;
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFileHandler(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {
		
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				
				// Create directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				
				if (!dir.exists())
					dir.mkdirs();
				
				//UUID to provide unique path for the file location
				 UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");     
			    
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name+uid.randomUUID());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
		
				logger.info("Server File Location= "+ serverFile.getAbsolutePath());
				
				
				ImageData imagedata=new ImageData();
				imagedata.setImagePath(serverFile.toString());
				this.imageDataBusiness.saveImageData(imagedata);
				
				return "You successfully uploaded file=" + name+ "on location :"+imagedata;
				
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name+ " You have not selected any file.";
		}
	}
	
}
