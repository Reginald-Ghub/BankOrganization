/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankorganization;
import java.io.*;
import java.util.*;


/**
 *
 * @author USER
 */
public class FileUtil {


    // This method reads applicant names from a text file.
    // If the file is missing, a dummy list is automatically used.
    public static List<String> readApplicantsFromFile(String filename) {
        List<String> names = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // remove extra spaces
                if (!line.isEmpty()) {
                    names.add(line);
                }
            }
            System.out.println(" File read successfully: " + filename);
        } catch (IOException e) {
            System.out.println(" File not found! Using dummy list instead.");

            // fallback dummy names (used if Applicants_Form.txt not found)
            names.addAll(Arrays.asList(
                "John Joe", "Sarah Connor", "Michael Smith", "Anna White",
                "Robert Green", "Linda Scott", "Chris Brown", "Mary Johnson",
                "David Gray", "Emily Stone", "Brian Black", "Sophia King",
                "Olivia Hall", "Ethan Brown", "Grace Evans", "Jacob Lewis",
                "Mia Clark", "Daniel Young", "Ella Harris", "James Turner"
            ));
        }

        return names;
    }

    // Recursive merge sort for sorting names alphabetically
    public static List<String> mergeSort(List<String> list) {
        if (list.size() <= 1) {
            return list; // base case
        }

        int mid = list.size() / 2;
        List<String> left = new ArrayList<>(list.subList(0, mid));
        List<String> right = new ArrayList<>(list.subList(mid, list.size()));

        return merge(mergeSort(left), mergeSort(right));
    }

    // Merge helper function combines two sorted lists
    private static List<String> merge(List<String> left, List<String> right) {
        List<String> result = new ArrayList<>();
        int i = 0, j = 0;

        // Compare and merge until one side is empty
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareToIgnoreCase(right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        // Add remaining elements
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;
    }

    // Display the first N names (e.g., top 20)
    public static void displayFirstN(List<String> list, int n) {
        System.out.println("\n Sorted Applicants List (first " + n + "):");
        for (int i = 0; i < Math.min(n, list.size()); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    static List<String> mergeSortNames(List<String> names) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    static List<String> readLinesFromFile(String defaultFile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}