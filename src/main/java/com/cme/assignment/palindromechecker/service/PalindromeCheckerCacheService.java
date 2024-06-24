package com.cme.assignment.palindromechecker.service;

import com.cme.assignment.palindromechecker.entity.PalindromeCheckerEntity;
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

    @Autowired
    PalindromeCheckerDaoService daoService;

    @Bean
    public CacheManager getCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(List.of(
                new ConcurrentMapCache("Palindrome")));
        return cacheManager;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadCache() {
        for(PalindromeCheckerEntity entity : daoService.findAll()) {
            Objects.requireNonNull(getCacheManager().getCache("Palindrome")).put(entity.getText(), entity.isPalindrome());
        }
    }
}
