package com.tenzin.studentmanagement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    @Test
    void testStudentCreation() {
        Student student = new Student(1, "Tenzin", 21, "Bengaluru", "tenzin@gmail.com");

        assertEquals(1, student.getId());
        assertEquals("Tenzin", student.getName());
        assertEquals(21, student.getAge());
        assertEquals("Bengaluru", student.getCity());
        assertEquals("tenzin@gmail.com", student.getEmail());
    }

}