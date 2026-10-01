package com.tenzin.studentmanagement;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        System.out.println("=== Student Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Student");
        System.out.println("3. Search Student By Id");
        System.out.println("4. Exit");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                // CREATE: Add a student
                System.out.print("Enter Student Id: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Student Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Student Age: ");
                int age = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Student City: ");
                String city = sc.nextLine();

                System.out.print("Enter Student Email: ");
                String email = sc.nextLine();

                Student student = new Student(id, name, age, city, email);
                dao.addStudent(student);

                System.out.println("Student Added Successfully!");
                break;

            case 2:
                // READ: Display all student
                List<Student> students = dao.getAllStudents();

                if (students.isEmpty()) {
                    System.out.println("Student not found!");
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
                System.out.print("Enter student id to search: ");
                int searchId = sc.nextInt();

                dao.getStudentById(searchId);
                break;

            case 4:
                System.out.println("Exiting Student Management System");
                break;

            default:
                System.out.println("Invalid choice!");
        }

        sc.close();
    }
}
