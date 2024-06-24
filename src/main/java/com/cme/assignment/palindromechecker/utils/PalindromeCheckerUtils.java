package com.cme.assignment.palindromechecker.utils;


import com.cme.assignment.palindromechecker.dto.PalindromeCheckerRequestDTO;
import com.cme.assignment.palindromechecker.dto.PalindromeCheckerResponseDTO;
import com.cme.assignment.palindromechecker.service.PalindromeCheckerDaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

@Service
public class PalindromeCheckerUtils {

    Logger logger = LoggerFactory.getLogger(PalindromeCheckerUtils.class);

    @Autowired
    private PalindromeCheckerDaoService daoService;

    /**
     * Method returns whether a given string is Palindrome or not
     * This method does a case sensitive check
     */
    public boolean checkPalindrome(String text) {
        if(StringUtils.isEmpty(text)) {
            return true;
        }

        // First and last index of the String
        int lowerIndex = 0;
        int upperIndex = text.length()-1;

        // Looping till the middle
        while(lowerIndex < upperIndex) {
            Character lowerCharacter = text.charAt(lowerIndex);
            Character upperCharacter = text.charAt(upperIndex);

            // Comparison of characters
            if(!lowerCharacter.equals(upperCharacter)) {
                return false;
            }

            // Updating indices
            lowerIndex++;
            upperIndex--;
        }

        // If matching till middle it is a valid palindrome
        return true;
    }

    /**
     * Wrapper method that caches the result and saves it asynchronously
     */
    @Cacheable("PalindromeCheckerCache")
    public Boolean isPalindrome(String text) {
        logger.debug(String.format("Cache miss for %s", text));
        boolean isPalindrome = checkPalindrome(text);
        daoService.saveAsync(text, isPalindrome);
        return isPalindrome;
    }

    /**
     * Method constructs response entity
     */
    public PalindromeCheckerResponseDTO buildSuccessResponse(PalindromeCheckerRequestDTO request, boolean isPalindrome) {
        String message = StringUtils.EMPTY;
        if(isPalindrome) {
            message = String.format("%s is a Palindrome", request.getText());
        } else {
            message = String.format("%s is not a Palindrome", request.getText());
        }
        return PalindromeCheckerResponseDTO.builder()
                .status(PalindromeCheckerResponseDTO.Status.SUCCESS)
                .isPalindrome(isPalindrome)
                .message(message)
                .build();
    }
}
