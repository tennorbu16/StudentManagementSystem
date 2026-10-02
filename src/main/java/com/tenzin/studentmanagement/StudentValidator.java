package com.tenzin.studentmanagement;

import java.util.Scanner;

public class StudentValidator {

    public static boolean isValidAge(int age) {
        return age >= 1 && age <= 100;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        String emailPattern =
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        return email.matches(emailPattern);
    }


    // Read an integer without crashing on text input
    public static int readInt(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a whole number.");
            }
        }
    }

    // Reject empty or whitespace-only value
    public static String readNonEmpty(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Name cannot be empty! Please try again.");
        }
    }

    // Accept age from 1 to 100
    public static int readAge(Scanner sc) {
        while (true) {
            int age = readInt(sc, "Enter age (1-100): ");

            if (isValidAge(age)) {
                return age;
            }

            System.out.println("Invalid age! Age must be between 1 and 100.");
        }
    }

    // Basic email format validation
    public static String readEmail(Scanner sc) {
        while (true) {
            String email = readNonEmpty(sc, "Enter student email: ");

            if (isValidEmail(email)) {
                return email;
            }

            System.out.println("Invalid email format! Please try again.");
        }
    }
}
