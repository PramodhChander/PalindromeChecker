package com.cme.assignment.palindromechecker.utils;


import com.cme.assignment.palindromechecker.dto.PalindromeCheckerRequestDTO;
import com.cme.assignment.palindromechecker.dto.PalindromeCheckerResponseDTO;
import com.cme.assignment.palindromechecker.service.PalindromeCheckerDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

@Service
public class PalindromeCheckerUtils {

    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Error";

    @Autowired
    private PalindromeCheckerDaoService daoService;

    @Cacheable("Palindrome")
    public Boolean isPalindrome(String text) {
        boolean isPalindrome = checkPalindrome(text);
        daoService.saveAsync(text, isPalindrome);
        return isPalindrome;
    }

    public boolean checkPalindrome(String text) {
        if(StringUtils.isEmpty(text)) {
            return true;
        }

        int lowerIndex = 0;
        int upperIndex = text.length()-1;
        while(lowerIndex < upperIndex) {
            Character lowerCharacter = text.charAt(lowerIndex);
            Character upperCharacter = text.charAt(upperIndex);
            if(!lowerCharacter.equals(upperCharacter)) {
                return false;
            }
            lowerIndex++;
            upperIndex--;
        }
        return true;
    }

    public PalindromeCheckerResponseDTO buildSuccessResponse(PalindromeCheckerRequestDTO request, boolean isPalindrome) {
        PalindromeCheckerResponseDTO response = new PalindromeCheckerResponseDTO();
        response.setStatus(PalindromeCheckerResponseDTO.Status.SUCCESS);
        response.setIsPalindrome(isPalindrome);
        String message = "";
        if(isPalindrome) {
            message = String.format("%s is a Palindrome", request.getText());
        } else {
            message = String.format("%s is not a Palindrome", request.getText());
        }
        response.setMessage(message);
        return response;
    }
}
