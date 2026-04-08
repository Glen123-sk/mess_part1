package com.mycompany.messenger;

import java.util.Scanner;
import java.util.regex.*;

class Messenger {

    // ----- LOGIN CLASS -----
    static class Login {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String cellPhoneNumber;

        // Constructor
        public Login(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        // Check username
        public boolean checkUserName(String username) {
            this.username = username;
            return username.contains("_") && username.length() <= 5;
        }

        // Check password complexity
        public boolean checkPasswordComplexity(String password) {
            this.password = password;
            String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!&*]).{8,}$";
            Pattern pattern = Pattern.compile(passwordPattern);
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        }

        // Check cell phone number
        public boolean checkCellPhoneNumber(String phoneNumber) {
            this.cellPhoneNumber = phoneNumber;
            String phonePattern = "^\\+27\\d{9}$"; // +27 followed by 9 digits
            Pattern pattern = Pattern.compile(phonePattern);
            Matcher matcher = pattern.matcher(phoneNumber);
            return matcher.matches();
        }

        // Register user (returns success message only)
        public String registerUser() {
            return "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
        }

        // Login user
        public boolean loginUser(String username, String password) {
            return this.username.equals(username) && this.password.equals(password);
        }

        // Return login status message
        public String returnLoginStatus(boolean loginSuccess) {
            if (loginSuccess) {
                return "Welcome " + this.firstName + ", " + this.lastName + " it is great to see you again.";
            } else {
                return "Username or password incorrect, please try again.";
            }
        }
    }

    // ----- MAIN METHOD -----
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Collect first and last name
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        Login login = new Login(firstName, lastName);

        // ----- REGISTRATION -----
        String username;
        while (true) {
            System.out.print("Enter username (contains _ and max 5 chars): ");
            username = scanner.nextLine();
            if (login.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure it contains an underscore and is no more than five characters.");
            }
        }

        String password;
        while (true) {
            System.out.print("Enter password (8+ chars, 1 capital, 1 number, 1 special char): ");
            password = scanner.nextLine();
            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure it has at least eight characters, a capital letter, a number, and a special character.");
            }
        }

        String phoneNumber;
        while (true) {
            System.out.print("Enter the last 9 digits of your South African cell phone number (e.g., 831234567): +27");
            String digits = scanner.nextLine();
            phoneNumber = "+27" + digits;
            if (digits.matches("\\d{9}") && login.checkCellPhoneNumber(phoneNumber)) {
                System.out.println("Cell number successfully captured.");
                break;
            } else {
                System.out.println("Cell number is incorrectly formatted; please enter exactly 9 digits after +27.");
            }
        }

        System.out.println("\nRegistration complete!");


        // ----- LOGIN -----
        System.out.println("\n--- LOGIN ---");
        while (true) {
            System.out.print("Enter username: ");
            String loginUsername = scanner.nextLine();
            if (!loginUsername.equals(login.username)) {
                System.out.println("Username or password incorrect, please try again.\n");
                continue;
            }
            // Username is correct, now ask for password until correct
            while (true) {
                System.out.print("Enter password: ");
                String loginPassword = scanner.nextLine();
                boolean loginSuccess = login.loginUser(loginUsername, loginPassword);
                System.out.println(login.returnLoginStatus(loginSuccess));
                if (loginSuccess) {
                    scanner.close();
                    return;
                } else {
                    System.out.println("Please try again.\n");
                }
            }
        }
    }
}