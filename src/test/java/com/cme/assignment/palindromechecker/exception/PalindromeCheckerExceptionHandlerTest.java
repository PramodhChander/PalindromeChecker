package com.cme.assignment.palindromechecker.exception;

import com.cme.assignment.palindromechecker.PalindromeCheckerBaseTest;
import com.cme.assignment.palindromechecker.utils.PalindromeCheckerUtils;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static com.cme.assignment.palindromechecker.dto.PalindromeCheckerResponseDTO.Status.FAILED;
import static com.cme.assignment.palindromechecker.dto.PalindromeCheckerResponseDTO.Status.ERROR;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class PalindromeCheckerExceptionHandlerTest extends PalindromeCheckerBaseTest {

    @MockBean
    private PalindromeCheckerUtils mockUtils;

    @Test
    public void testHandleValidationExceptions_withException() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\" : \"Pramodh\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RESPONSE_STATUS_PATH, is(FAILED.toString())))
                .andExpect(jsonPath(RESPONSE_MESSAGE_PATH, is("Validation Error: text field is mandatory and cannot contain numbers or whitespaces")));
        ;
    }

    @Test
    public void testHandleInvalidRequestExceptions_withException() throws Exception {
        mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RESPONSE_STATUS_PATH, is(FAILED.toString())))
                .andExpect(jsonPath(RESPONSE_MESSAGE_PATH, is("Error: Invalid Request")));
    }

    @Test
    public void testHandleGeneralExceptions_withException() throws Exception {
        String dummyExceptionMessage = "ExceptionMessage";
        when(mockUtils.isPalindrome(anyString())).thenThrow(new RuntimeException(dummyExceptionMessage));
        mockMvc.perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(VALID_PALINDROME_REQUEST))
                .andExpect(status().is5xxServerError())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RESPONSE_STATUS_PATH, is(ERROR.toString())))
                .andExpect(jsonPath(RESPONSE_MESSAGE_PATH, is("Error: " + dummyExceptionMessage)));
    }

}
