package com.cme.assignment.palindromechecker.validation;

import com.cme.assignment.palindromechecker.PalindromeCheckerBaseTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class PalindromeCheckerInputValidatorTest extends PalindromeCheckerBaseTest {

    PalindromeCheckerInputValidator validator = new PalindromeCheckerInputValidator();

    @Test
    public void testIsValid_nullValue() {
        assertFalse(validator.isValid(null));
    }

    @Test
    public void testIsValid_emptyString() {
        assertFalse(validator.isValid(StringUtils.EMPTY));
    }

    @Test
    public void testIsValid_validValue() {
        assertTrue(validator.isValid("test"));
    }

    @Test
    public void testIsValid_withNumbers() {
        assertFalse(validator.isValid("test123"));
    }

    @Test
    public void testIsValid_withSpace() {
        assertFalse(validator.isValid("test abc"));
    }
}
