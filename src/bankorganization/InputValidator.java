/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankorganization;
import java.util.Scanner;

    /*
     * Utility class for validating user input in console applications.
     * Provides methods to safely read strings and integers from the user.
     */
class InputValidator {

    // Reads a non-empty string from the user.
    // Keeps prompting until the user enters something valid (non-empty).
    public static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {  // Infinite loop until valid input
            System.out.print(prompt);  // Display the message to user
            String input = scanner.nextLine().trim();  // Read line and remove extra spaces
            if (!input.isEmpty()) {  // Check if input is not empty
                return input;  // Valid input: return it
            } else {
                System.out.println("Input cannot be empty. Please try again.");  // Error message
            }
        }
    }
    // Reads an integer from the user within a specific range [min, max].
    // Keeps prompting until the user enters a valid number in range.
    public static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {  // Infinite loop until valid input
            System.out.print(prompt);  // Display message
            String line = scanner.nextLine().trim();  // Read input as string
            try {
                int value = Integer.parseInt(line);  // Convert string to integer
                if (value >= min && value <= max) {  // Check if value is within range
                    return value;  // Valid input: return it
                } else {
                    System.out.printf("Please enter a number between %d and %d.%n", min, max);
                }
            } catch (NumberFormatException e) {  // Input was not a valid integer
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

     // Inner Department class (optional)
    // Can also be made a top-level class if preferred
    // Models a department with id, name, and optional description
    public static class Department {
        private final int id;  // Unique ID of the department
        private final String name;  // Department name
        private final String description;  // Optional description

        public Department(int id, String name, String description) {
            this.id = id;  // Assign department ID
            this.name = name;  // Assign department name
            this.description = description;  // Assign department description
        }

        public int getId() { return id; }  // Return department ID
        public String getName() { return name; }  // Return department name
        public String getDescription() { return description; }  // Return description

        // toString method
        // Returns a string representation of the department
        // In this case, only the name is returned for simplicity
        @Override
        public String toString() { return name; }
    }
}