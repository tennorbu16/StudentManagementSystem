package com.tenzin.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDAO {
    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (id, name, age, city, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection connect = JdbcUtil.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)){
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

        try (Connection connect = JdbcUtil.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql);
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

        try (Connection connect = JdbcUtil.getConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

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
}
