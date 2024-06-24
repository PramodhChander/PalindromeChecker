package com.cme.assignment.palindromechecker.dto;

import com.cme.assignment.palindromechecker.validation.PalindromeCheckerInputValidator;
import com.cme.assignment.palindromechecker.validation.Validate;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PalindromeCheckerRequestDTO {

    @NotBlank(message = "userName is mandatory")
    private String userName;

    @Validate(fieldValidator = PalindromeCheckerInputValidator.class, message = "text cannot contain numbers or whitespaces")
    private String text;
}
