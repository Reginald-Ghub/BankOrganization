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


// Enum representing the terminal menu options

    ADD_EMPLOYEE,       // Add a new employee manually
    GENERATE_RANDOM,    // Generate random employees
    SORT_APPLICANTS,    // Sort names from Applicants_Form.txt
    SEARCH,             // Search for an employee by name
    DISPLAY_ALL,        // Display all employees
    EXIT                // Exit the program
}
public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankOrganization org = new BankOrganization(); // create your organization instance

        while (true) {
            System.out.println("\nDo You Wish to:");
            System.out.println("1. SORT Applicants");
            System.out.println("2. SEARCH for a name");
            System.out.println("3. ADD RECORDS");
            System.out.println("4. GENERATE RANDOM Employees");
            System.out.println("5. EXIT");

            int choice = InputValidator.readIntInRange(scanner, "Enter your choice (1-5): ", 1, 5);
            MenuOption option = MenuOption.values()[choice - 1];

            switch (option) {
                case SORT_APPLICANTS -> BankOrganization.sortApplicants(scanner);
                case SEARCH -> org.searchApplicant(scanner);
                case GENERATE_RANDOM -> org.generateRandomApplicants();
                case EXIT -> {
                    System.out.println(" Exiting program. Goodbye!");
                    scanner.close();
                    return;
                }
            }
        }
    }

}
