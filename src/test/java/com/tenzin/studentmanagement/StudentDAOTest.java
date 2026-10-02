package com.tenzin.studentmanagement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {

    private Connection connection;
    private StudentDAO dao;

    @BeforeEach
    void setUp() throws SQLException {
        connection = JdbcUtilTest.getConnection();
        dao = new StudentDAO(connection);
    }

    @AfterEach
    void cleanup() throws SQLException {
        connection.createStatement()
                .executeUpdate("DELETE FROM students");

        connection.close();
    }

    @Test
    void addStudentShouldInsertStudent() throws SQLException {

        Student student = new Student(
                1,
                "Tenzin",
                21,
                "Bengaluru",
                "tenzin@test.com"
        );

        dao.addStudent(student);

        var result = connection.createStatement()
                .executeQuery("SELECT * FROM students WHERE id = 1");

        assertTrue(result.next());

        assertEquals(1, result.getInt("id"));
        assertEquals("Tenzin", result.getString("name"));
        assertEquals(21, result.getInt("age"));
        assertEquals("Bengaluru", result.getString("city"));
        assertEquals("tenzin@test.com", result.getString("email"));

        result.close();
    }

    @Test
    void getAllStudentsShouldReturnStudents() throws SQLException {

        Student student1 = new Student(
                1,
                "Tenzin",
                21,
                "Bengaluru",
                "tenzin1@test.com"
        );

        Student student2 = new Student(
                2,
                "John",
                22,
                "Delhi",
                "john@test.com"
        );

        dao.addStudent(student1);
        dao.addStudent(student2);

        List<Student> students = dao.getAllStudents();

        assertEquals(2, students.size());

        assertEquals("Tenzin", students.get(0).getName());
        assertEquals("John", students.get(1).getName());
    }

    @Test
    void updateStudentShouldChangeAge() throws SQLException {

        Student student = new Student(
                1,
                "Tenzin",
                21,
                "Bengaluru",
                "tenzin@test.com"
        );

        dao.addStudent(student);

        dao.updateStudent(1, 25);

        var result = connection.createStatement()
                .executeQuery("SELECT age FROM students WHERE id = 1");

        assertTrue(result.next());
        assertEquals(25, result.getInt("age"));

        result.close();
    }

    @Test
    void deleteStudentShouldRemoveStudent() throws SQLException {

        Student student = new Student(
                1,
                "Tenzin",
                21,
                "Bengaluru",
                "tenzin@test.com"
        );

        dao.addStudent(student);

        dao.deleteStudent(1);

        var result = connection.createStatement()
                .executeQuery("SELECT * FROM students WHERE id = 1");

        assertFalse(result.next());

        result.close();
    }
}