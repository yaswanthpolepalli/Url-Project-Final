package com.ctsproject.urlshortener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ctsproject.urlshortener.controller.UrlController;
import com.ctsproject.urlshortener.entity.Urls;

import junit.framework.Assert;



@SpringBootTest
class UrlshortenerApplicationTests {

	/*
	@Test
	void contextLoads() {
	}
	*/
	

	Urls url=new Urls();
	
	@Autowired
	private UrlController urlController;
	
	@Test
	void testTopUrls() {
		Assert.assertNotNull(urlController.getTopUrls());
	}
	
	
	
	@Test
	void testRecentUrls() {
		Assert.assertNotNull(urlController.getRecentUrls());

	}
	
	@Test
	void testGetUrls() {
		Assert.assertNotNull(urlController.getUrls());

	}
	
	

}
