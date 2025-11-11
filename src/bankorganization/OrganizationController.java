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
    private void sortApplicantsFromFile() {
    String defaultFile = "Applicants_Form.txt"; // expected file
    List<String> names = FileUtil.readLinesFromFile(defaultFile);

    // If file is missing or empty, use dummy data
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

    // Menu for sort/search/add
    System.out.println("Do you wish to SORT or SEARCH the applicants?");
    System.out.println("1. SORT");
    System.out.println("2. SEARCH");
    System.out.println("3. ADD NEW APPLICANT");
    System.out.println("4. EXIT");
        Scanner scanner = null;
    int choice = InputValidator.readIntInRange(scanner, "Select (1-4): ", 1, 4);

        switch (choice) {
            case 1 -> {
                // SORT
                System.out.println("\n--- Sorted Applicant List ---");
                List<String> sorted = FileUtil.mergeSortNames(names);
                for (int i = 0; i < sorted.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, sorted.get(i));
                }
            }
            case 2 -> {
                // SEARCH
                String toFind = InputValidator.readNonEmptyString(scanner, "Enter name to search: ");
                boolean found = false;
                for (String nm : names) {
                    if (nm.equalsIgnoreCase(toFind)) {
                        System.out.println(" Applicant found: " + nm);
                        found = true;
                        break;
                    }
                }       if (!found) System.out.println(" Applicant not found in list.");
            }
            case 3 -> {
                // ADD NEW APPLICANT
                String newApplicant = InputValidator.readNonEmptyString(scanner, "Enter new applicant name: ");
                names.add(newApplicant);
                System.out.println(" Added new applicant: " + newApplicant);
                System.out.println("Updated applicant list:");
                for (String nm : names) {
                    System.out.println("- " + nm);
                }
            }
            default -> System.out.println("Returning to main menu...");
        }
}

}
