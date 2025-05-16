package com.demo.testng.program.pojo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.demo.testng.program.connection.PostgresConnectionPool;
import com.demo.testng.program.memory_cache.EmployeeApi;

public class User {
    public static final String TAG_VALID_USER = "VALID_USER";
    public static final String TAG_INVALID_USER = "INVALID_USER";

    private String userGroup;
    private String email;
    private String password;

    public User(String userGroup, String email, String password) {
        this.userGroup = userGroup;
        this.email = email;
        this.password = password;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void applyDataFromDatabase() throws Exception {
        try (Connection conn = PostgresConnectionPool.getConnection()) {
            System.out.println("Connected to PostgreSQL successfully!");
            // Check if the connection is valid
            if (conn == null || conn.isClosed()) {
                throw new SQLException("Failed to establish a connection to the database.");
            }
            // Connection is valid, proceed with your database operations
            String query_select_test_user_automation_table = "SELECT test_group, email_user, password_user FROM public.test_user_automation;";
            try (Statement stmt = conn.createStatement()) {
                // Execute the query
                ResultSet rs = stmt.executeQuery(query_select_test_user_automation_table);
                System.out.println("Query executed successfully.");
                while (rs.next()) {
                    String testGroup = rs.getString("test_group");
                    String email = rs.getString("email_user");
                    String password = rs.getString("password_user");
                    System.out.println(testGroup + ", " + email + ", " + password);
                    if (testGroup.equals(TAG_VALID_USER)) {
                        if (EmployeeApi.validUsers == null) {
                            EmployeeApi.validUsers = new ArrayList<>();
                        }
                        EmployeeApi.validUsers.add(new User(testGroup, email, password));
                    } else if (testGroup.equals(TAG_INVALID_USER)) {
                        if (EmployeeApi.invalidUsers == null) {
                            EmployeeApi.invalidUsers = new ArrayList<>();
                        }
                        EmployeeApi.invalidUsers.add(new User(testGroup, email, password));
                    }
                }
            } catch (SQLException e) {
                throw new SQLException("Error executing query: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new SQLException("Error executing query: " + e.getMessage(), e);
        } finally {
            // Close the connection pool
            PostgresConnectionPool.closePool();
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
