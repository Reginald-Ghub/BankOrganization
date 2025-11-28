/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankorganization;

import java.util.Scanner;

/**
 *
 * @author USER
 */

public class MainMenu {
    
public enum MenuOption {


    // Enum representing all available menu options in the program
    // Enum makes it easy to manage options and avoid magic numbers

    ADD_EMPLOYEE,       // Add a new employee manually
    GENERATE_RANDOM,    // Generate random employees
    SORT_APPLICANTS,    // Sort names from Applicants_Form.txt
    SEARCH,             // Search for an employee by name
    DISPLAY_ALL,        // Display all employees
    EXIT                // Exit the program
}
// Main method: Entry point of the program
// Displays the menu and executes the selected action in a loop
public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Scanner for reading user input
        BankOrganization org = new BankOrganization();  // Create an instance of the organization

        while (true) {  // Infinite loop for menu until user exits
            System.out.println("\nDo You Wish to:");
            System.out.println("1. SORT Applicants");
            System.out.println("2. SEARCH for a name");
            System.out.println("3. ADD RECORDS");
            System.out.println("4. GENERATE RANDOM Employees");
            System.out.println("5. EXIT");
            
            // Read user's choice and validate it is between 1 and 5
            int choice = InputValidator.readIntInRange(scanner, "Enter your choice (1-5): ", 1, 5);
            // Map the user's numeric choice to the corresponding enum value
            MenuOption option = MenuOption.values()[choice - 1];
            
            // Execute action based on user's choice
            switch (option) {
                case SORT_APPLICANTS -> BankOrganization.sortApplicants(scanner);  // Call static method to sort applicants
                case SEARCH -> org.searchApplicant(scanner);  // Search for an employee by name
                case GENERATE_RANDOM -> org.generateRandomApplicants();  // Generate random employees
                case EXIT -> {  // Exit option
                    System.out.println(" Exiting program. Goodbye!");
                    scanner.close();  // Close the scanner to release resources
                    return;  // Exit the main method (end program)
                }
            }
        }
    }

}
