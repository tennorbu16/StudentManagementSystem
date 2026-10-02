package com.tenzin.studentmanagement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentValidatorTest {
    @Test
    void validAgeShouldReturnTrue() {
        assertTrue(StudentValidator.isValidAge(21));
    }

    @Test
    void ageZeroShouldReturnFalse() {
        assertFalse(StudentValidator.isValidAge(0));
    }

    @Test
    void ageAbove100ShouldReturnFalse() {
        assertFalse(StudentValidator.isValidAge(101));
    }

    @Test
    void validNameShouldReturnTrue() {
        assertTrue(StudentValidator.isValidName("Tenzin"));
    }

    @Test
    void emptyNameShouldReturnFalse() {
        assertFalse(StudentValidator.isValidName(""));
    }

    @Test
    void spacesOnlyNameShouldReturnFalse() {
        assertFalse(StudentValidator.isValidName("   "));
    }

    @Test
    void validEmailShouldReturnTrue() {
        assertTrue(StudentValidator.isValidEmail("tenzin@gmail.com"));
    }

    @Test
    void invalidEmailShouldReturnFalse() {
        assertFalse(StudentValidator.isValidEmail("tenzin@gmail"));
    }

    @Test
    void emptyEmailShouldReturnFalse() {
        assertFalse(StudentValidator.isValidEmail(""));
    }

}