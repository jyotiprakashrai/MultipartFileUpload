package com.springmvc.jyoti.business;

import java.util.List;

import com.springmvc.jyoti.model.ImageData;


public interface ImageDataBusiness {
	
	public void saveImageData(ImageData imagedata);
	
	public List<ImageData> getImageData();

}
