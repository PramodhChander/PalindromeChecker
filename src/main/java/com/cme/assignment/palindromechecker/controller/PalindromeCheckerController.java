package com.cme.assignment.palindromechecker.controller;

import com.cme.assignment.palindromechecker.utils.PalindromeCheckerUtils;
import com.cme.assignment.palindromechecker.dto.PalindromeCheckerRequestDTO;
import com.cme.assignment.palindromechecker.dto.PalindromeCheckerResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/palindromechecker/api/v1/")
public class PalindromeCheckerController {

    @Autowired
    private PalindromeCheckerUtils plaindromeCheckerUtils;

    /**
     * Main api method processing request
     */
    @PostMapping("/isPalindrome")
    public PalindromeCheckerResponseDTO isPalindrome(@Valid @RequestBody PalindromeCheckerRequestDTO request) {
        boolean result = plaindromeCheckerUtils.isPalindrome(request.getText());
        return plaindromeCheckerUtils.buildSuccessResponse(request, result);
    }
}
