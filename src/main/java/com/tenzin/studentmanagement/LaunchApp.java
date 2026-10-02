package com.tenzin.studentmanagement;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static com.tenzin.studentmanagement.StudentValidator.*;

public class LaunchApp {
    public static void main(String[] args) throws SQLException {
        try (Scanner sc = new Scanner(System.in)) {
            StudentDAO dao = new StudentDAO();
            boolean running = true;

            while (running) {
                System.out.println("=== Student Management System ===");
                System.out.println("1. Add Student");
                System.out.println("2. Display All Student");
                System.out.println("3. Search Student By Id");
                System.out.println("4. Update Student");
                System.out.println("5. Delete Student");
                System.out.println("6. Exit");

                int choice = readInt(sc, "Enter your choice: ");

                try {
                    switch (choice) {
                        case 1:
                            // CREATE: Add a student
                            int id = readInt(sc, "Enter student Id: ");
                            String name = readNonEmpty(sc, "Enter student name: ");
                            int age = readAge(sc);
                            String city = readNonEmpty(sc, "Enter student city: ");
                            String email = readEmail(sc);

                            Student student = new Student(id, name, age, city, email);

                            dao.addStudent(student);
                            System.out.println("Student Added Successfully!");
                            break;

                        case 2:
                            // READ: Display all student
                            List<Student> students = dao.getAllStudents();

                            if (students.isEmpty()) {
                                System.out.println("No students found!");
                            } else {
                                for (Student s : students) {
                                    System.out.println("Student Id: " + s.getId());
                                    System.out.println("Student Name: " + s.getName());
                                    System.out.println("Student Age: " + s.getAge());
                                    System.out.println("Student City: " + s.getCity());
                                    System.out.println("Student Email: " + s.getEmail());
                                    System.out.println();
                                }

                            }
                            break;

                        case 3:
                            // READ: Search student by id
                            int searchId = readInt(sc, "Enter student id to search: ");
                            dao.getStudentById(searchId);
                            break;

                        case 4:
                            // UPDATE: Update a student's age using their ID
                            int updateId = readInt(sc, "Enter student Id: ");
                            int updateAge = readAge(sc);

                            dao.updateStudent(updateId, updateAge);
                            break;

                        case 5:
                            // DELETE: Delete a student's record using their Id
                            int deleteId = readInt(sc, "Enter student Id: ");

                            dao.deleteStudent(deleteId);
                            break;

                        case 6:
                            System.out.println("Exiting Student Management System");
                            running = false;
                            break;

                        default:
                            System.out.println("Invalid choice! Please enter a number from 1 to 6");
                    }
                } catch (SQLException e) {
                    // SQL error code 1062 means a duplicate key
                    if (e.getErrorCode() == 1062) {
                        System.out.println("Duplicate value! The student Id or email may already exist!");
                    } else {
                        System.out.println("Database operation failed: " + e.getMessage());
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("Application error: " + e.getMessage());
        }
    }
}
