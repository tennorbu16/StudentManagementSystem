package com.tenzin.studentmanagement;

public class TestStudent {
    public static void main(String[] args) {
        Student s1 = new Student(
                1, "Tenzin", 21, "Bengaluru", "tenzin123@gmail.com"
        );

        System.out.println(s1.getName());
        System.out.println(s1.getAge());
    }
}
