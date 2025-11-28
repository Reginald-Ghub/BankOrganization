/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankorganization;
import bankorganization.MainMenu.MenuOption;
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

// This class represents the main program for the Bank Organization system.
    // It loads applicants, lets the user add employees, sort/search employees,
    // and generates random employees for testing.
 

    // List to store all employees in memory
    private static final List<Employee> employees = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in); // Scanner object used for reading keyboard input

    public static void main(String[] args) {
        // Load applicant names from a text file when the program starts
        loadApplicants("Applicants_Form.txt");

        // Display menu until user exits
        boolean running = true; // Controls the main menu loop
        // Loop until the user chooses "EXIT"
        while (running) {
            System.out.println("\n--- Bank Organization Menu ---");           
            // Display all options from the MenuOption enum
            int i = 1;
            for (MenuOption option : MenuOption.values()) {
                System.out.println(i + ". " + option);
                i++;
            }
            // Ask user for a valid menu option numbe
            int choice = InputValidator.readIntInRange(scanner, "Select an option: ", 1, MenuOption.values().length);
            MenuOption selected = MenuOption.values()[choice - 1];

            // Perform action based on the selected menu option
            switch (selected) {
                case ADD_EMPLOYEE -> addEmployee();
                case GENERATE_RANDOM -> generateRandomEmployees();
                case SORT_APPLICANTS -> sortApplicants(scanner);
                case SEARCH -> searchEmployee();
                case DISPLAY_ALL -> displayAllEmployees();
                case EXIT -> {
                    System.out.println("Exiting program. Goodbye!");
                    running = false;
                }
            }
        }
    }

    // Load applicants from a text file and add as employees with random managers & departments
    private static void loadApplicants(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int id = 1; // Employee ID counter
            Random rand = new Random();
            Department[] depts = { // Predefined list of departments
                    new Department(1, "Customer Service", ""),
                    new Department(2, "Foreign Exchange", ""),
                    new Department(3, "HR", ""),
                    new Department(4, "Finance", ""),
                    new Department(5, "IT", "")
            };
            // Read each line (each applicant name)
            while ((line = br.readLine()) != null) {
                line = line.trim();
                // Skip empty lines
                if (!line.isEmpty()) {
                    // Random manager assignment
                    Manager manager;
                    int mgrChoice = rand.nextInt(3);
                    manager = switch (mgrChoice) {
                        case 0 -> new HeadManager(id, "Manager " + id);
                        case 1 -> new AssistantManager(id, "Manager " + id);
                        default -> new TeamLead(id, "Manager " + id);
                    };

                    // Random department
                    Department dept = depts[rand.nextInt(depts.length)];
                    // Create a new employee and add to list
                    employees.add(new Employee(id, line, manager, dept));
                    id++;
                }
            }
            System.out.println("Applicants loaded successfully. Total: " + employees.size());
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
    }

    // Display all employees in the list
    private static void displayAllEmployees() {
        System.out.println("\n--- All Employees ---");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    // Allows user to manually add one employee by typing
    // name, choosing manager type, and choosing a department.
    private static void addEmployee() {
        System.out.println("\n--- Add New Employee ---");
        int id = employees.size() + 1;
        String name = InputValidator.readNonEmptyString(scanner, "Enter employee name: ");
        // Choose manager type
        System.out.println("Select Manager Type: 1. Head Manager 2. Assistant Manager 3. Team Lead");
        int mgrChoice = InputValidator.readIntInRange(scanner, "Choice: ", 1, 3);
        Manager manager;
        manager = switch (mgrChoice) {
            case 1 -> new HeadManager(id, "Manager " + id);
            case 2 -> new AssistantManager(id, "Manager " + id);
            default -> new TeamLead(id, "Manager " + id);
        };
        // Choose department
        System.out.println("Select Department: 1. Customer Service 2. Foreign Exchange 3. HR 4. Finance 5. IT");
        int deptChoice = InputValidator.readIntInRange(scanner, "Choice: ", 1, 5);
        Department dept;
        switch (deptChoice) {
            case 1 -> dept = new Department(1, "Customer Service", "");
            case 2 -> dept = new Department(2, "Foreign Exchange", "");
            case 3 -> dept = new Department(3, "HR", "");
            case 4 -> dept = new Department(4, "Finance", "");
            default -> dept = new Department(5, "IT", "");
        }
        // Add new employee to list
        employees.add(new Employee(id, name, manager, dept));
        System.out.println("Employee added successfully!");
    }

    // Generates a number of random employees using random
    // names from Applicants_Form.txt.
    private static void generateRandomEmployees() {
    System.out.println("\n--- Generate Random Employees ---");
    System.out.print("How many random employees to generate? ");
    int n = InputValidator.readIntInRange(scanner, "", 1, 100);

    // Load names from Applicants_Form.txt
    List<String> names = loadNamesFromFile("Applicants_Form.txt");
    if (names.isEmpty()) {
        System.out.println("No names found in Applicants_Form.txt. Please check the file.");
        return;
    }

    Random rand = new Random();
    Department[] depts = {
        new Department(1, "Customer Service", ""),
        new Department(2, "Foreign Exchange", ""),
        new Department(3, "HR", ""),
        new Department(4, "Finance", ""),
        new Department(5, "IT", "")
    };
    // Create random employees
    for (int i = 0; i < n; i++) {
        int id = employees.size() + 1;
        String name = names.get(rand.nextInt(names.size())); // pick random name from file

        Manager manager;
        int mgrChoice = rand.nextInt(3); // Random manager type
        manager = switch (mgrChoice) {
            case 0 -> new HeadManager(id, "Manager " + id);
            case 1 -> new AssistantManager(id, "Manager " + id);
            default -> new TeamLead(id, "Manager " + id);
        };

        Department dept = depts[rand.nextInt(depts.length)]; // Random department
        employees.add(new Employee(id, name, manager, dept));
    }

    System.out.println(n + " random employees generated successfully!");
}


    // Sort applicants alphabetically using recursive Merge Sort
    static void sortApplicants(Scanner scanner1) {
        System.out.println("\n--- Sorting Applicants ---");
        mergeSort(employees, 0, employees.size() - 1);
        System.out.println("Top 20 employees (alphabetical):");
        for (int i = 0; i < Math.min(20, employees.size()); i++) {
            System.out.println(employees.get(i).getName());
        }
    }

    // Recursive merge sort algorithm
    private static void mergeSort(List<Employee> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid); // Sort left half
            mergeSort(list, mid + 1, right); // Sort right half
            merge(list, left, mid, right); // Combine sorted halves
        }
    }
    // Merge two sorted halves
    private static void merge(List<Employee> list, int left, int mid, int right) {
        List<Employee> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        // Merge based on alphabetical name comparison
        while (i <= mid && j <= right) {
            if (list.get(i).getName().compareToIgnoreCase(list.get(j).getName()) <= 0) {
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
            }
        }
        // Add remaining left half
        while (i <= mid) temp.add(list.get(i++));
        // Add remaining right half
        while (j <= right) temp.add(list.get(j++));
        // Copy back into original list
        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }

    // Search for an employee by name using binary search.
    // List must be sorted first.
    private static void searchEmployee() {
        System.out.println("\n--- Search Employee ---");
        System.out.print("Enter name to search: ");
        String target = scanner.nextLine().trim();

        // Ensure list is sorted before binary search
        mergeSort(employees, 0, employees.size() - 1);

        int index = binarySearch(employees, target);
        if (index >= 0) {
            System.out.println("Employee found: " + employees.get(index));
        } else {
            System.out.println("Employee not found.");
        }
    }

    // Standard binary search algorithm
    private static int binarySearch(List<Employee> list, String target) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = list.get(mid).getName().compareToIgnoreCase(target);
            if (cmp == 0) return mid; // Found match
            else if (cmp < 0) left = mid + 1; // Search right side
            else right = mid - 1; // Search left side
        }
        return -1;
    }
    // Helper method to load names from Applicants_Form.txt into a List
private static List<String> loadNamesFromFile(String filename) {
    List<String> names = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                names.add(line);
            }
        }
    } catch (IOException e) {
        System.out.println("Error loading names from file: " + filename);
    }
    return names;
}
    // Not implemented placeholders
    void searchApplicant(Scanner scanner) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    void generateRandomApplicants() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}