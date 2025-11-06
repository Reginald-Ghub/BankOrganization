/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankorganization;

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

}