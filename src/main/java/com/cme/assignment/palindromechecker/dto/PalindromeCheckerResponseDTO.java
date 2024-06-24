package com.cme.assignment.palindromechecker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PalindromeCheckerResponseDTO {
    public enum Status {
        SUCCESS("Success"),
        FAILED("Failed"),
        ERROR("Error");

        private final String statusCode;

        private Status(String value) {
            statusCode = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return  statusCode;
        }
    };

    private Status status;
    private Boolean isPalindrome;
    private String message;

    @JsonProperty(value="isPalindrome")
    public Boolean isPalindrome() {
        return isPalindrome;
    }
}
