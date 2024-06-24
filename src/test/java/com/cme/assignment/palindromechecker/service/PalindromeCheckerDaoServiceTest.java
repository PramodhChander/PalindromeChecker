package com.cme.assignment.palindromechecker.service;

import com.cme.assignment.palindromechecker.PalindromeCheckerBaseTest;
import com.cme.assignment.palindromechecker.entity.PalindromeCheckerEntity;
import com.cme.assignment.palindromechecker.repository.PalindromeCheckerRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PalindromeCheckerDaoServiceTest extends PalindromeCheckerBaseTest {
    @Mock
    private PalindromeCheckerRepository mockRepository;

    @InjectMocks
    private PalindromeCheckerDaoService mockDaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAsync() throws Exception {
        boolean isPalindrome = true;
        PalindromeCheckerEntity entity = PalindromeCheckerEntity.builder()
                .text(VALID_PALINDROME)
                .isPalindrome(isPalindrome)
                .build();

        when(mockRepository.save(any(PalindromeCheckerEntity.class)))
                .thenReturn(entity);

        CompletableFuture<PalindromeCheckerEntity> future = mockDaoService.saveAsync(VALID_PALINDROME, isPalindrome);
        PalindromeCheckerEntity result = future.get();
        assertNotNull(result);
        assertEquals(VALID_PALINDROME, result.getText());
        assertEquals(isPalindrome, result.isPalindrome());
    }

    @Test
    public void testFindAll() {
        when(mockRepository.findAll()).thenReturn(getMockEntities());
        List<PalindromeCheckerEntity> result = mockDaoService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(VALID_PALINDROME, result.get(0).getText());
        assertTrue(result.get(0).isPalindrome());
        assertEquals(NON_PALINDROME, result.get(1).getText());
        assertFalse(result.get(1).isPalindrome());
    }
}
