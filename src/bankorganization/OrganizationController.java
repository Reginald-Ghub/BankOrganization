/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankorganization;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class OrganizationController {
    // Method to load applicants from file, sort them, search, or add new ones
    private void sortApplicantsFromFile() {
    String defaultFile = "Applicants_Form.txt"; // Expected file containing applicants
    List<String> names = FileUtil.readLinesFromFile(defaultFile); // Read lines from file

    // Fallback: If file is missing or empty, use dummy data
    if (names.isEmpty()) {
        System.out.println(" No file found. Using dummy applicant list instead.");
        names = new ArrayList<>(List.of(
                "Alice Johnson",
                "Bob Smith",
                "Carol Brown",
                "David Miller",
                "Emma Davis",
                "Frank Wilson",
                "Grace Thompson",
                "Harry White",
                "Ivy Taylor",
                "Jack Anderson",
                "Lily Thomas",
                "Mike Harris",
                "Nina Martin",
                "Oscar Garcia",
                "Paul Lewis"
        ));
    }

    // Menu for user to choose action
    System.out.println("Do you wish to SORT or SEARCH the applicants?");
    System.out.println("1. SORT");
    System.out.println("2. SEARCH");
    System.out.println("3. ADD NEW APPLICANT");
    System.out.println("4. EXIT");
        Scanner scanner = null;
        // It must be initialized before reading input:
    int choice = InputValidator.readIntInRange(scanner, "Select (1-4): ", 1, 4);

    // Execute action based on user's choice
        switch (choice) {
            case 1 -> {
                // SORT
                System.out.println("\n--- Sorted Applicant List ---");
                List<String> sorted = FileUtil.mergeSortNames(names); // Sort names alphabetically
                for (int i = 0; i < sorted.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, sorted.get(i)); // Display sorted list with numbering
                }
            }
            case 2 -> {
                // SEARCH
                String toFind = InputValidator.readNonEmptyString(scanner, "Enter name to search: ");
                boolean found = false;
                for (String nm : names) { // Loop through all names
                    if (nm.equalsIgnoreCase(toFind)) { // Case-insensitive comparison
                        System.out.println(" Applicant found: " + nm);
                        found = true;
                        break; // Stop after finding the first match
                    }
                }       if (!found) System.out.println(" Applicant not found in list."); // If not found
            }
            case 3 -> {
                // ADD NEW APPLICANT
                String newApplicant = InputValidator.readNonEmptyString(scanner, "Enter new applicant name: ");
                names.add(newApplicant); // Add new applicant to list
                System.out.println(" Added new applicant: " + newApplicant);
                System.out.println("Updated applicant list:");
                for (String nm : names) {
                    System.out.println("- " + nm);
                }
            }
            default -> System.out.println("Returning to main menu..."); // Option 4 or unexpected choice
        }
}

}
