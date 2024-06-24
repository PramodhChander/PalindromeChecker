package com.cme.assignment.palindromechecker.service;

import com.cme.assignment.palindromechecker.entity.PalindromeCheckerEntity;
import com.cme.assignment.palindromechecker.repository.PalindromeCheckerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PalindromeCheckerDaoService {

    Logger logger = LoggerFactory.getLogger(PalindromeCheckerDaoService.class);

    @Autowired
    PalindromeCheckerRepository palindromeCheckerRepository;

    /**
     * Dao method to save entry into DB asynchronously
     */
    @Async
    public CompletableFuture<PalindromeCheckerEntity> saveAsync(String text, boolean isPalindrome) {
        logger.info(String.format("Saving entity async for text %s with result %s", text, isPalindrome));
        PalindromeCheckerEntity entity = PalindromeCheckerEntity
                .builder()
                .text(text)
                .isPalindrome(isPalindrome)
                .build();
        PalindromeCheckerEntity savedEntity = palindromeCheckerRepository.save(entity);
        return CompletableFuture.completedFuture(savedEntity);
    }

    /**
     * Dao method to get all existing records from DB
     */
    public List<PalindromeCheckerEntity> findAll() {
        return (List<PalindromeCheckerEntity>) palindromeCheckerRepository.findAll();
    }

}
