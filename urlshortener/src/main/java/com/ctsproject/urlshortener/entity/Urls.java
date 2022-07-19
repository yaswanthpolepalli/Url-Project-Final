package com.ctsproject.urlshortener.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Urls {

	
	public Urls() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long urlId;
	
	@Column
	private Long id; 
	
	private String shortUrl;
	private String longUrl;

	private LocalDateTime creationDate;

	private LocalDateTime lastAccessDate;
	 
	private Long accessCount;

	public Long getUrlId() {
		return urlId;
	}

	public void setUrlId(Long urlId) {
		this.urlId = urlId;
	}

	public Long getId() {
		return id;
	}

	public void setid(Long userId) {
		this.id = userId;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(LocalDateTime lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public Long getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(Long accessCount) {
		this.accessCount = accessCount;
	}

	

	@Override
	public String toString() {
		return "Urls [urlId=" + urlId + ", id=" + id + ", shortUrl=" + shortUrl + ", longUrl=" + longUrl
				+ ", creationDate=" + creationDate + ", lastAccessDate=" + lastAccessDate + ", accessCount="
				+ accessCount + "]";
	}

	public Urls(Long urlId, Long userId, String shortUrl, String longUrl, LocalDateTime creationDate, LocalDateTime lastAccessDate,
			Long accessCount) {
		super();
		this.urlId = urlId;
		this.id = userId;
		this.shortUrl = shortUrl;
		this.longUrl = longUrl;
		this.creationDate = creationDate;
		this.lastAccessDate = lastAccessDate;
		this.accessCount = accessCount;
	}

	

	
	
}
