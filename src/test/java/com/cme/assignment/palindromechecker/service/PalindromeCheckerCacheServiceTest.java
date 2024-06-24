package com.cme.assignment.palindromechecker.service;

import com.cme.assignment.palindromechecker.PalindromeCheckerBaseTest;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;

import static com.cme.assignment.palindromechecker.service.PalindromeCheckerCacheService.CACHE_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {PalindromeCheckerCacheService.class})
public class PalindromeCheckerCacheServiceTest extends PalindromeCheckerBaseTest {

    @MockBean
    private PalindromeCheckerDaoService mockDaoService;

    @Autowired
    private PalindromeCheckerCacheService mockCacheService;

    @Autowired
    private CacheManager mockCacheManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadCache() {
        when(mockDaoService.findAll()).thenReturn(getMockEntities());
        mockCacheService.loadCache();

        Cache cache = mockCacheManager.getCache(CACHE_NAME);
        assertNotNull(cache);
        assertEquals(true, cache.get(VALID_PALINDROME).get());
        assertEquals(false, cache.get(NON_PALINDROME).get());
    }
}
