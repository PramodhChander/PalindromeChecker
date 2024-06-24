package com.cme.assignment.palindromechecker.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="PalindromeCheckerTable")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PalindromeCheckerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean isPalindrome;

}
