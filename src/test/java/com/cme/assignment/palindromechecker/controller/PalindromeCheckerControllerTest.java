package com.cme.assignment.palindromechecker.controller;

import com.cme.assignment.palindromechecker.PalindromeCheckerBaseTest;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static com.cme.assignment.palindromechecker.dto.PalindromeCheckerResponseDTO.Status.FAILED;
import static com.cme.assignment.palindromechecker.dto.PalindromeCheckerResponseDTO.Status.SUCCESS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class PalindromeCheckerControllerTest extends PalindromeCheckerBaseTest {

    @Test
    public void testIsPalindromeApi_validPalindromeRequest() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(VALID_PALINDROME_REQUEST))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RESPONSE_STATUS_PATH, is(SUCCESS.toString())))
                .andExpect(jsonPath(RESPONSE_PALINDROME_FLAG_PATH, is(true)));
    }

    @Test
    public void testIsPalindromeApi_invalidPalindromeRequest() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RESPONSE_STATUS_PATH, is(FAILED.toString())));
    }

}
