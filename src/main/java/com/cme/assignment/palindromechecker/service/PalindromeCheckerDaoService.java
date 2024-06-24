package com.cme.assignment.palindromechecker.service;

import com.cme.assignment.palindromechecker.entity.PalindromeCheckerEntity;
import com.cme.assignment.palindromechecker.repository.PalindromeCheckerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PalindromeCheckerDaoService {

    @Autowired
    PalindromeCheckerRepository palindromeCheckerRepository;

    @Async
    public CompletableFuture<PalindromeCheckerEntity> saveAsync(String text, boolean isPalindrome) {
        PalindromeCheckerEntity entity = PalindromeCheckerEntity
                .builder()
                .text(text)
                .isPalindrome(isPalindrome)
                .build();
        PalindromeCheckerEntity savedEntity = palindromeCheckerRepository.save(entity);
        return CompletableFuture.completedFuture(savedEntity);
    }

    public List<PalindromeCheckerEntity> findAll() {
        return (List<PalindromeCheckerEntity>) palindromeCheckerRepository.findAll();
    }

}
