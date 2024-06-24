package com.cme.assignment.palindromechecker.repository;

import com.cme.assignment.palindromechecker.entity.PalindromeCheckerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PalindromeCheckerRepository extends CrudRepository<PalindromeCheckerEntity, Integer> {
}
