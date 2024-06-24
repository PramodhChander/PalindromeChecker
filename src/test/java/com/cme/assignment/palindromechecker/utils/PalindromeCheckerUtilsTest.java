package com.cme.assignment.palindromechecker.utils;

import com.cme.assignment.palindromechecker.PalindromeCheckerBaseTest;
import com.cme.assignment.palindromechecker.dto.PalindromeCheckerRequestDTO;
import com.cme.assignment.palindromechecker.dto.PalindromeCheckerResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;

import java.util.List;

import static com.cme.assignment.palindromechecker.service.PalindromeCheckerCacheService.CACHE_NAME;
import static org.junit.Assert.*;

@SpringBootTest
public class PalindromeCheckerUtilsTest extends PalindromeCheckerBaseTest {

    @Autowired
    private PalindromeCheckerUtils mockUtils;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(List.of(new ConcurrentMapCache(CACHE_NAME)));
        cacheManager = simpleCacheManager;
    }

    @Test
    public void testCheckPalindrome_emptyString() {
        assertTrue(mockUtils.checkPalindrome(StringUtils.EMPTY));
    }

    @Test
    public void testCheckPalindrome_validPalindrome() {
        assertTrue(mockUtils.checkPalindrome(VALID_PALINDROME));
    }

    @Test
    public void testCheckPalindrome_nonPalindrome() {
        assertFalse(mockUtils.checkPalindrome(NON_PALINDROME));
    }

    @Test
    public void testBuildSuccessResponse_validPalindrome() {
        PalindromeCheckerRequestDTO requestDTO = PalindromeCheckerRequestDTO.builder().text(VALID_PALINDROME).userName("user").build();
        PalindromeCheckerResponseDTO responseDTO = mockUtils.buildSuccessResponse(requestDTO, true);

        assertEquals(PalindromeCheckerResponseDTO.Status.SUCCESS, responseDTO.getStatus());
        assertTrue(responseDTO.getIsPalindrome());
        assertEquals("kayak is a Palindrome", responseDTO.getMessage());
    }

    @Test
    public void testBuildSuccessResponse_nonPalindrome() {
        PalindromeCheckerRequestDTO requestDTO = PalindromeCheckerRequestDTO.builder().text(NON_PALINDROME).userName("user").build();
        PalindromeCheckerResponseDTO responseDTO = mockUtils.buildSuccessResponse(requestDTO, false);

        assertEquals(PalindromeCheckerResponseDTO.Status.SUCCESS, responseDTO.getStatus());
        assertFalse(responseDTO.getIsPalindrome());
        assertEquals("kayaking is not a Palindrome", responseDTO.getMessage());
    }
}
