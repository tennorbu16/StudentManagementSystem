package com.tenzin.studentmanagement;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {
        try (Connection connect = JdbcUtil.getConnection()) {
            System.out.println("MySQL connection successfully!");
        }
        catch (SQLException e) {
            System.out.println("MySQL connection failed!");
            e.printStackTrace();
        }
    }
}