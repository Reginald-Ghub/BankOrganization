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
    
    // Simple data class representing a bank department.
public class Department {
    private int id;           // unique id for the department
    private String name;      // human-readable name (e.g., "Customer Service")
    private String description; // short description of responsibilities

    // Constructor to initialize department fields
    public Department(int id, String name, String description) {
        this.id = id;                 // set id
        this.name = name;             // set name
        this.description = description; // set description
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // toString returns the department name for easy display
    @Override
    public String toString() {
        return name;
    }
}
}

// Abstract parent class representing a manager role.
// Concrete roles extend this class: HeadManager, AssistantManager, TeamLead.
public abstract class Manager {
    protected int id;       // unique manager id (for registry)
    protected String title; // title string used for display (e.g., "Head Manager")
    protected String name;  // a name or descriptor (optional)
    protected int level;    // numeric level useful for ordering (higher = more senior)

    // Constructor for manager base class
    public Manager(int id, String title, String name, int level) {
        this.id = id;           // set id
        this.title = title;     // set title
        this.name = name;       // set name
        this.level = level;     // set level
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for level
    public int getLevel() {
        return level;
    }

    // toString returns the title for menu displays
    @Override
    public String toString() {
        return title;
    }
}

// Concrete manager subclass for Head Manager
class HeadManager extends Manager {
    public HeadManager(int id, String name) {
        super(id, "Head Manager", name, 3); // level 3 for highest
    }
}

// Concrete manager subclass for Assistant Manager
class AssistantManager extends Manager {
    public AssistantManager(int id, String name) {
        super(id, "Assistant Manager", name, 2); // level 2
    }
}

// Concrete manager subclass for Team Lead
class TeamLead extends Manager {
    public TeamLead(int id, String name) {
        super(id, "Team Lead", name, 1); // level 1 for team lead
    }
}
}