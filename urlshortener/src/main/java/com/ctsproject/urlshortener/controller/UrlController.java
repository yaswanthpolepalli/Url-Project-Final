package com.ctsproject.urlshortener.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ctsproject.urlshortener.entity.Urls;
import com.ctsproject.urlshortener.service.UrlShorteningService;

@RestController
@RequestMapping("urls")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.DELETE, RequestMethod.PUT })

public class UrlController {

	@Autowired
	UrlShorteningService urlShorteningService;

	// Shortening up the URL
	@PostMapping("/short")
	public Urls saveShortUrl(@RequestBody Urls urls) {
		urls.setCreationDate(LocalDateTime.now());
		return urlShorteningService.saveUrl(urls);
	}

	// Expanding up the URL
	@GetMapping("/urlByShort/{url}")
	public ResponseEntity<Urls> findUrlByShort(@PathVariable("url") String shortUrl) {
		Urls answer = urlShorteningService.findUrlsByShortUrl(shortUrl);
		System.out.println("Url");
		System.out.println(answer.getLongUrl());
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<Urls> entity = new ResponseEntity<>(answer, headers, HttpStatus.ACCEPTED);
		return entity;
	}

	// Top URLs
	@GetMapping("/gettopurls")
	public List<Urls> getTopUrls() {
		return urlShorteningService.getTop20Urls();
	}

	// Recent URLs
	@GetMapping("/getrecenturls")
	public List<Urls> getRecentUrls() {
		return urlShorteningService.getRecent20Urls();
	}

	// All URLs
	@GetMapping("/getallurls")
	public List<Urls> getUrls() {
		return urlShorteningService.getAllUrls();
	}

	@GetMapping("/{urls}")
	public void findUrlByShorts(@PathVariable("urls") String shortUrl, HttpServletResponse httpServlet)
			throws IOException {
		Urls answer = urlShorteningService.findUrlsByShortUrl(shortUrl);
		urlShorteningService.updateCountandDate(answer);
		httpServlet.sendRedirect(answer.getLongUrl());
	}

	@Scheduled(cron = "0 00 09 * * *")
	public void delete() {
		urlShorteningService.deleteUrl();
	}

}
