package com.ctsproject.urlshortener.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctsproject.urlshortener.dao.UrlRepository;
import com.ctsproject.urlshortener.entity.Urls;

@Service
public class UrlShortenerServiceImpl implements UrlShorteningService {

	@Autowired
	UrlRepository urlRepository;

	@PersistenceContext
	private EntityManager entityManager;

	private String answer = "";

	public String shortUrl(String URL) {

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] input = URL.getBytes("UTF-8");
			byte[] md5hash = messageDigest.digest(input);
			Base64.Encoder encoder = Base64.getEncoder();
			String encodeToString = encoder.encodeToString(md5hash);
			int shortKeyType = 3;
			String tinyUrlKey = shortKeyType == 1 ? encodeToString.substring(0, 6)
					: shortKeyType == 2 ? encodeToString.substring(0, 8) : randomlySelect8Chars(encodeToString);
			answer = tinyUrlKey;
			System.out.println("ShortKey --> " + answer);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("algo");
		} catch (UnsupportedEncodingException e) {
			System.out.println("encoding");
		}

		if (answer.contains("/")) {
			shortUrl(URL);
		}
		return answer;
	}

	private static String randomlySelect8Chars(String encodeToString) {
		Random random = ThreadLocalRandom.current();
		char[] encodedChars = encodeToString.toCharArray();
		assert encodedChars.length == 21;
		for (int i = 20; i >= 0; i--) {
			int randomIndex = random.nextInt(i + 1);
			swap(encodedChars, randomIndex, i);
		}
		return new String(encodedChars, 0, 8);
	}

	private static void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}

	@Override
	public Urls saveUrl(Urls urls) {
		System.out.println("urls shortening occurs..");
		String shortUrl = shortUrl(urls.getLongUrl());
		urls.setShortUrl(shortUrl);
		return urlRepository.save(urls);
	}

	@Override
	public Urls findUrlsByShortUrl(String shortUrl) {
		// TODO Auto-generated method stub
		return urlRepository.findUrlsByShortUrl(shortUrl);
	}

	@Override
	public List<Urls> getTop20Urls() {

		return urlRepository.getUrlsTop20();
	}

	@Override
	public List<Urls> getRecent20Urls() {

		return urlRepository.getUrlsRecent20();
	}

	@Override
	public List<Urls> getAllUrls() {
		return urlRepository.findAll();
	}

	@Override
	public Urls updateCountandDate(Urls url) {
		Urls u = urlRepository.findUrlsByUrlId(url.getUrlId());
		u.setAccessCount(u.getAccessCount() + 1);
		u.setLastAccessDate(LocalDateTime.now());
		return urlRepository.save(u);
	}

	@Override
	public void deleteUrl() {

		List<Urls> list = entityManager.createNativeQuery(
				"select * from  urls t where t.last_access_date < now() - interval 30 DAY", Urls.class).getResultList();
		System.out.println("delete rows");
		urlRepository.deleteAll(list);

	}

}
