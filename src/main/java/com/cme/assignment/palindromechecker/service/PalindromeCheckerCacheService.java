package com.cme.assignment.palindromechecker.service;

import com.cme.assignment.palindromechecker.entity.PalindromeCheckerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.Objects;

@Configuration
@EnableCaching
public class PalindromeCheckerCacheService {

    Logger logger = LoggerFactory.getLogger(PalindromeCheckerCacheService.class);

    @Autowired
    PalindromeCheckerDaoService palindromeCheckerDaoService;

    public static final String CACHE_NAME = "PalindromeCheckerCache";

    /**
     * Method for Caching using Concurrent Map
     */
    @Bean
    public CacheManager getCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(
                new ConcurrentMapCache(CACHE_NAME)));
        return cacheManager;
    }

    /**
     * Bootup method to load cache records from DB file
     */
    @EventListener(ApplicationReadyEvent.class)
    public void loadCache() {
        logger.info("Loading Cache PalindromeCheckerCache");
        List<PalindromeCheckerEntity> entityList = palindromeCheckerDaoService.findAll();
        for(PalindromeCheckerEntity entity : entityList) {
            Objects.requireNonNull(getCacheManager().getCache(CACHE_NAME)).put(entity.getText(), entity.isPalindrome());
        }
        logger.info(String.format("Loaded %d records into Cache", entityList.size()));
    }
}
