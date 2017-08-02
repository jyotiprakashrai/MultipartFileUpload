package com.springmvc.jyoti.dao;

import java.util.List;

import com.springmvc.jyoti.model.ImageData;

public interface ImageDataDao {
	

	public void saveImageData(ImageData imageData);
	
	
	
     public List<ImageData> getImageData();

}
