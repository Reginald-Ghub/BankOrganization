/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankorganization;
import java.util.Scanner; // Scanner used for console input
import java.util.Random; // Random used to pick names
import java.io.BufferedReader; // for efficient file reading
import java.io.FileReader;     // to open the file
import java.io.IOException;    // for handling IO errors
import java.util.ArrayList;    // list implementation
import java.util.List;         // list interface

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
// Employee class represents each staff member in the bank organization.
// Each employee has an ID, name, assigned manager, and department.
class Employee {
    private int id;                  // unique ID for the employee
    private String name;             // employee name
    private Manager manager;         // assigned manager role (Head, Assistant, or Team Lead)
    private InputValidator.Department department; // assigned department (nested from InputValidator)

    // Constructor initializes all fields when creating an employee
    public Employee(int id, String name, Manager manager, InputValidator.Department department) {
        this.id = id;               // store employee ID
        this.name = name;           // store employee name
        this.manager = manager;     // store assigned manager type
        this.department = department; // store assigned department
    }

    // Getter for employee ID
    public int getId() {
        return id;
    }

    // Getter for employee name
    public String getName() {
        return name;
    }

    // Getter for assigned manager
    public Manager getManager() {
        return manager;
    }

    // Getter for assigned department
    public InputValidator.Department getDepartment() {
        return department;
    }

    // Setter for employee name (in case of edits)
    public void setName(String name) {
        this.name = name;
    }

    // Setter for manager role
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    // Setter for department
    public void setDepartment(InputValidator.Department department) {
        this.department = department;
    }

    // toString method to return a readable employee summary for display
    @Override
    public String toString() {
        return String.format("Employee ID: %d | Name: %s | Manager: %s | Department: %s",
                id, name, manager.getTitle(), department.getName());
    }
}

// Utility to generate random first and last name combinations.
public class RandomNameGenerator {

    // Small arrays of sample first and last names for generating test data
    private static final String[] FIRST_NAMES = {
            "John", "Jane", "Alice", "Bob", "Carol", "David", "Emma", "Frank",
            "Grace", "Harry", "Ivy", "Jack", "Lily", "Mike", "Nina", "Oscar"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Miller",
            "Davis", "Garcia", "Rodriguez", "Wilson", "Martinez"
    };

    private static final Random RANDOM = new Random(); // single Random instance

    // Build a full name by choosing one first name and one last name randomly
    public static String generateRandomName() {
        String first = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)]; // pick random first
        String last = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];    // pick random last
        return first + " " + last; // return combined full name
    }
}
// Utility class for reading applicant file and performing merge-sort on strings.
public class FileUtil {

    // Read all non-empty trimmed lines from a file into a list of strings.
    public static List<String> readLinesFromFile(String filename) {
        List<String> lines = new ArrayList<>(); // result list
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) { // try-with-resources auto-closes
            String line; // temporary storage for each read line
            while ((line = br.readLine()) != null) { // read until EOF
                if (!line.trim().isEmpty()) { // skip empty lines
                    lines.add(line.trim()); // add trimmed line
                }
            }
        } catch (IOException e) {
            // If file not found or other IO error occurs, inform the user
            System.out.println("Error reading file: " + e.getMessage());
        }
        return lines; // return possibly empty list
    }
    // Public method to perform merge sort on a list of String names
    public static List<String> mergeSortNames(List<String> list) {
        if (list.size() <= 1) { // base case: already sorted
            return list;
        }
        int mid = list.size() / 2; // midpoint
        // Recursively sort left half and right half
        List<String> left = mergeSortNames(new ArrayList<>(list.subList(0, mid)));
        List<String> right = mergeSortNames(new ArrayList<>(list.subList(mid, list.size())));
        return merge(left, right); // merge sorted halves
    }

    // Merge two sorted lists of strings into a single sorted list
    private static List<String> merge(List<String> left, List<String> right) {
        List<String> result = new ArrayList<>(); // merged result
        int i = 0, j = 0; // indices for left and right lists
        while (i < left.size() && j < right.size()) { // while both lists have elements
            // case-insensitive comparison for alphabetical ordering
            if (left.get(i).compareToIgnoreCase(right.get(j)) <= 0) {
                result.add(left.get(i++)); // append left element then increment i
            } else {
                result.add(right.get(j++)); // append right element then increment j
            }
        }
        while (i < left.size()) result.add(left.get(i++)); // append remaining left
        while (j < right.size()) result.add(right.get(j++)); // append remaining right
        return result; // return merged list
    }
}
}
