package com.springmvc.jyoti.business;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.jyoti.dao.ImageDataDao;
import com.springmvc.jyoti.model.ImageData;


@Service
public class ImageDataBusinessImpl implements ImageDataBusiness {
	
	
	private ImageDataDao imageDataDao;

	
	public ImageDataDao getImageDataDao() {
		return imageDataDao;
	}

	public void setImageDataDao(ImageDataDao imageDataDao) {
		this.imageDataDao = imageDataDao;
	}

	@Override
	@Transactional
	public void saveImageData(ImageData imagedata) {
		this.imageDataDao.saveImageData(imagedata);

	}

	@Override
	@Transactional
	public List<ImageData> getImageData() {
		// TODO Auto-generated method stub
		return null;
	}

}
