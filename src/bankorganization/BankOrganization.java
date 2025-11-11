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

// Main class that runs the Bank Organization console application
 

    // List to store all employees in memory
    private static List<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Load applicants from file
        loadApplicants("Applicants_Form.txt");

        // Display menu until user exits
        boolean running = true;
        while (running) {
            System.out.println("\n--- Bank Organization Menu ---");
            int i = 1;
            for (MenuOption option : MenuOption.values()) {
                System.out.println(i + ". " + option);
                i++;
            }
            int choice = InputValidator.readIntInRange(scanner, "Select an option: ", 1, MenuOption.values().length);
            MenuOption selected = MenuOption.values()[choice - 1];

            switch (selected) {
                case ADD_EMPLOYEE -> addEmployee();
                case GENERATE_RANDOM -> generateRandomEmployees();
                case SORT_APPLICANTS -> sortApplicants();
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
            int id = 1;
            Random rand = new Random();
            Department[] depts = {
                    new Department(1, "Customer Service", ""),
                    new Department(2, "Foreign Exchange", ""),
                    new Department(3, "HR", ""),
                    new Department(4, "Finance", ""),
                    new Department(5, "IT", "")
            };

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    // Random manager assignment
                    Manager manager;
                    int mgrChoice = rand.nextInt(3);
                    if (mgrChoice == 0) manager = new HeadManager(id, "Manager " + id);
                    else if (mgrChoice == 1) manager = new AssistantManager(id, "Manager " + id);
                    else manager = new TeamLead(id, "Manager " + id);

                    // Random department
                    Department dept = depts[rand.nextInt(depts.length)];

                    employees.add(new Employee(id, line, manager, dept));
                    id++;
                }
            }
            System.out.println("Applicants loaded successfully. Total: " + employees.size());
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
    }

    // Display all employees
    private static void displayAllEmployees() {
        System.out.println("\n--- All Employees ---");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    // Add a new employee manually
    private static void addEmployee() {
        System.out.println("\n--- Add New Employee ---");
        int id = employees.size() + 1;
        String name = InputValidator.readNonEmptyString(scanner, "Enter employee name: ");

        System.out.println("Select Manager Type: 1. Head Manager 2. Assistant Manager 3. Team Lead");
        int mgrChoice = InputValidator.readIntInRange(scanner, "Choice: ", 1, 3);
        Manager manager;
        switch (mgrChoice) {
            case 1:
                manager = new HeadManager(id, "Manager " + id);
                break;
            case 2:
                manager = new AssistantManager(id, "Manager " + id);
                break;
            default:
                manager = new TeamLead(id, "Manager " + id);
                break;
        }

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

        employees.add(new Employee(id, name, manager, dept));
        System.out.println("Employee added successfully!");
    }

    // Generate random employees
    private static void generateRandomEmployees() {
        System.out.println("\n--- Generate Random Employees ---");
        System.out.print("How many random employees to generate? ");
        int n = InputValidator.readIntInRange(scanner, "", 1, 100);
        Random rand = new Random();
        Department[] depts = {
                new Department(1, "Customer Service", ""),
                new Department(2, "Foreign Exchange", ""),
                new Department(3, "HR", ""),
                new Department(4, "Finance", ""),
                new Department(5, "IT", "")
        };

        for (int i = 0; i < n; i++) {
            int id = employees.size() + 1;
            String name = "Employee" + id;

            Manager manager;
            int mgrChoice = rand.nextInt(3);
            if (mgrChoice == 0) manager = new HeadManager(id, "Manager " + id);
            else if (mgrChoice == 1) manager = new AssistantManager(id, "Manager " + id);
            else manager = new TeamLead(id, "Manager " + id);

            Department dept = depts[rand.nextInt(depts.length)];
            employees.add(new Employee(id, name, manager, dept));
        }
        System.out.println(n + " random employees generated successfully!");
    }

    // Sort applicants alphabetically using recursive Merge Sort
    private static void sortApplicants() {
        System.out.println("\n--- Sorting Applicants ---");
        mergeSort(employees, 0, employees.size() - 1);
        System.out.println("Top 20 employees (alphabetical):");
        for (int i = 0; i < Math.min(20, employees.size()); i++) {
            System.out.println(employees.get(i).getName());
        }
    }

    // Merge Sort implementation for Employee list by name
    private static void mergeSort(List<Employee> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    private static void merge(List<Employee> list, int left, int mid, int right) {
        List<Employee> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) {
            if (list.get(i).getName().compareToIgnoreCase(list.get(j).getName()) <= 0) {
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
            }
        }
        while (i <= mid) temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));
        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }

    // Search employee by name using Binary Search
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

    // Binary search for employee by name
    private static int binarySearch(List<Employee> list, String target) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = list.get(mid).getName().compareToIgnoreCase(target);
            if (cmp == 0) return mid;
            else if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}