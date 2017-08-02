package com.springmvc.jyoti.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;

import com.springmvc.jyoti.model.ImageData;


@Repository
public class ImageDataDaoImpl implements ImageDataDao {
	
	private static final Logger logger=LoggerFactory.getLogger(ImageDataDaoImpl.class);
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory seeionFactory) {
		this.sessionFactory = seeionFactory;
       }

	@Override
	public void saveImageData(ImageData imageData) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(imageData);
		System.out.println("Saving to DB");
        logger.info("Image Details saved successfully");

	}

	@Override
	public List<ImageData> getImageData() {
		// Write criteria or query to retreve image from DB Here
		return null;
	}

}
