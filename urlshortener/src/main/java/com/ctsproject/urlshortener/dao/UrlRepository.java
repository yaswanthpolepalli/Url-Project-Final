package com.ctsproject.urlshortener.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ctsproject.urlshortener.entity.Urls;

@Repository
public interface UrlRepository extends JpaRepository<Urls, Long> {

	public Urls findUrlsByShortUrl(String shortUrl);

	public Urls findUrlsByUrlId(Long urlId);

	@Query(nativeQuery = true, value = "select * from urls order by access_count desc limit 20")
	List<Urls> getUrlsTop20();

	@Query(nativeQuery = true, value = "select * from urls order by creation_date desc  limit 20")
	List<Urls> getUrlsRecent20();

}
