/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankorganization;
import java.io.*;
import java.util.*;


    /*
 * Utility class for handling file operations and dummy data
 * in the Bank Organization project.
 *
 * Responsibilities:
 * - Read applicant names from a file
 * - Provide fallback dummy names if the file is missing
 * - Sort lists of names using recursive Merge Sort
 * - Display sorted names
 * - Generate random names for testing employees
 */
public class FileUtil {
    
    // Predefined dummy names
    // Used as fallback if Applicants_Form.txt is missing
    private static final String[] SAMPLE_NAMES = {
        "Alice Johnson", "Bob Smith", "Carol Brown", "David Miller", "Emma Davis",
        "Frank Wilson", "Grace Thompson", "Harry White", "Ivy Taylor", "Jack Anderson",
        "Lily Thomas", "Mike Harris", "Nina Martin", "Oscar Garcia", "Paul Lewis",
        "Sophia Adams", "Ethan Collins", "Chloe Rogers", "Liam Murphy"
    };

    // Random object for selecting names randomly
    private static final Random random = new Random();

    /**
     * Reads applicant names from a text file.
     * If the file is missing or unreadable, a dummy list is used instead.
     * @param filename
     * @return 
     */
    public static List<String> readApplicantsFromFile(String filename) {
        List<String> names = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {  
                line = line.trim();  // Remove leading/trailing spaces
                if (!line.isEmpty()) {  // Skip empty lines
                    names.add(line);  // Add valid line to list
                }
            }
            System.out.println("File read successfully: " + filename);
        } catch (IOException e) {
            System.out.println("File not found! Using dummy list instead.");

            // fallback dummy names (used if Applicants_Form.txt not found)
            names.addAll(Arrays.asList(SAMPLE_NAMES));
        }

        return names;
    }
    // Recursive Merge Sort for a List of Strings
    // Returns a new sorted List alphabetically (A-Z)
    public static List<String> mergeSort(List<String> list) {
        if (list.size() <= 1) {
            return list; // Base case: list of 0 or 1 element is already sorted
        }
        // Split list into left and right halves
        int mid = list.size() / 2;
        List<String> left = new ArrayList<>(list.subList(0, mid));
        List<String> right = new ArrayList<>(list.subList(mid, list.size()));
        
        // Recursively sort both halves and merge
        return merge(mergeSort(left), mergeSort(right));
    }

    // Helper function that merges two sorted lists alphabetically
    private static List<String> merge(List<String> left, List<String> right) {
        List<String> result = new ArrayList<>();
        int i = 0, j = 0;

        // Compare elements from both lists and add smaller to result
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareToIgnoreCase(right.get(j)) <= 0) {
                result.add(left.get(i++));  // Add from left if smaller or equal
            } else {
                result.add(right.get(j++));  // Add from right if smaller
            }
        }

        // Add remaining elements from left list
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;  // Return the merged sorted list
    }

    /**
     * Displays the first N names from a list on the console
     * Useful to show top applicants or sample data
     * @param list
     * @param n
     */
    public static void displayFirstN(List<String> list, int n) {
        System.out.println("\nSorted Applicants List (first " + n + "):");
        for (int i = 0; i < Math.min(n, list.size()); i++) {
            System.out.println((i + 1) + ". " + list.get(i));  // Print with index
        }
    }

    /**
     * Optional wrapper method to sort names using mergeSort
     * Can be called directly to sort a List of names
     * @param names
     * @return 
     */
    public static List<String> mergeSortNames(List<String> names) {
        return mergeSort(names);
    }

    /**
     * Reads all non-empty lines from a given file
     * Returns fallback list if file is missing
     * @param filename
     * @param fallback
     * @return 
     */
    public static List<String> readLinesFromFile(String filename, List<String> fallback) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {  // Skip empty lines
                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read file, using fallback data instead.");
            lines.addAll(fallback);  // Use fallback list if reading fails
        }
        return lines;  // Return the resulting list
    }

    /**
     * Returns a random name from the SAMPLE_NAMES array
     * Useful to generate random employees for testing
     * @return 
     */
    public static String getRandomName() {
        return SAMPLE_NAMES[random.nextInt(SAMPLE_NAMES.length)];
    }
    // Placeholder method
    static List<String> readLinesFromFile(String defaultFile) {
        throw new UnsupportedOperationException("Not supported yet.");
}
}
