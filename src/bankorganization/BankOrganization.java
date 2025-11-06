/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankorganization;
import java.util.Scanner; // Scanner used for console input

/**
 *
 * @author USER
 */
public class BankOrganization {

// Enum representing the available menu options in the console program.
// Using an enum enforces clear option names and makes menu code easier to maintain.   
public enum MenuOption {
    ADD_EMPLOYEE,        // Option to add a new employee manually
    GENERATE_RANDOM,     // Option to generate random employees
    SORT_APPLICANTS,     // Option to sort names from Applicants_Form.txt
    SEARCH,              // Option to search for an employee by name
    DISPLAY_ALL,         // Option to display all stored employees
    EXIT                 // Option to exit the program
}

// Utility class that centralizes console input validation.
// Keeping validation in one place simplifies Unit Testing and reuse.
public class InputValidator {

    // Read a non-empty string from the scanner with a prompt
    public static String readNonEmptyString(Scanner scanner, String prompt) {
        // loop until a non-empty trimmed string is provided
        while (true) {
            System.out.print(prompt);               // show prompt to user
            String input = scanner.nextLine().trim(); // read line and trim whitespace
            if (!input.isEmpty()) {                 // valid when not empty
                return input;                       // return valid input
            } else {
                System.out.println("Input cannot be empty. Please try again."); // error message
            }
        }
    }

    // Read an integer within inclusive range [min, max], with a prompt
    public static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        // loop until a valid integer in the required range is obtained
        while (true) {
            System.out.print(prompt); // show prompt
            String line = scanner.nextLine().trim(); // read as string
            try {
                int value = Integer.parseInt(line); // try parse int
                if (value >= min && value <= max) { // check range
                    return value;                   // valid, return
                } else {
                    // prompt again if outside range
                    System.out.printf("Please enter a number between %d and %d.%n", min, max);
                }
            } catch (NumberFormatException e) {
                // input not an integer
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}

}