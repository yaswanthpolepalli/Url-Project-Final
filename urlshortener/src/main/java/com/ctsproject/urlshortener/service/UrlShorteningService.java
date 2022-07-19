package com.ctsproject.urlshortener.service;

import java.util.List;

import com.ctsproject.urlshortener.entity.Urls;


public interface UrlShorteningService {

	public Urls saveUrl(Urls urls);

	public Urls findUrlsByShortUrl(String shortUrl);

	public List<Urls> getTop20Urls();

	public List<Urls> getRecent20Urls();

	public List<Urls> getAllUrls();

	public Urls updateCountandDate(Urls url);

	public void deleteUrl();

}
