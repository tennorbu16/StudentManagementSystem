package com.tenzin.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDAO {
    private Connection connection;

    // Used by the normal application
    public StudentDAO() {
    }

    // Used by JUnit tests
    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    private Connection getConnection() throws SQLException {
        if (connection != null) {
            return connection;
        }

        return JdbcUtil.getConnection();
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (id, name, age, city, email) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement prepare = getConnection().prepareStatement(sql)){

            prepare.setInt(1, student.getId());
            prepare.setString(2, student.getName());
            prepare.setInt(3, student.getAge());
            prepare.setString(4, student.getCity());
            prepare.setString(5, student.getEmail());

            prepare.executeUpdate();
        }

    }

    public List<Student> getAllStudents() throws SQLException {
        String sql = "SELECT * FROM students";

        try (PreparedStatement prepare = getConnection().prepareStatement(sql);
             ResultSet rs = prepare.executeQuery()) {

            List<Student> students = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String city = rs.getString("city");
                String email = rs.getString("email");

                Student student = new Student(id, name, age, city, email);
                students.add(student);
            }

            return students;
        }
    }

    public void getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id = ?";

        try (PreparedStatement prepare = getConnection().prepareStatement(sql)) {

            prepare.setInt(1, id);

            ResultSet rs = prepare.executeQuery();

            if (rs.next()) {
                int studentId = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String city = rs.getString("city");
                String email = rs.getString("email");

                System.out.println("Student Id: " + studentId);
                System.out.println("Student Name: " + name);
                System.out.println("Student Age: " + age);
                System.out.println("Student City: " + city);
                System.out.println("Student Email: " + email);
            } else {
                System.out.println("Student not found");
            }
        }
    }

    public void updateStudent(int id, int age) throws SQLException {
        String sql = "UPDATE students SET age = ? WHERE id = ?";

        try (PreparedStatement prepare = getConnection().prepareStatement(sql)) {

            prepare.setInt(1, age);
            prepare.setInt(2, id);

            int rowAffected = prepare.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Update failed! Student not found.");
            }
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";

        try (PreparedStatement prepare = getConnection().prepareStatement(sql)) {
            prepare.setInt(1, id);

            int rowAffected = prepare.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Delete failed! Student not found.");
            }
        }
    }
}
