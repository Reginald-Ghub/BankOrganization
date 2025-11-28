/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankorganization;

/**
 *
 * @author USER
 */
public class Department { // This class models a bank department such as HR, Finance, IT, etc.

    private final int id;             // Unique department ID
    private final String name;        // Department name (e.g., "HR")
    private final String description; // Stores extra details about the department (optional text)

    //  Constructor: This method runs when a new Department object
    // is created. It assigns values to the three private variables.
    public Department(int id, String name, String description) {
        this.id = id;  // Assign the passed 'id' value to this object's id variable
        this.name = name;  // Assign the passed 'name' value to this object's name variable
        this.description = description;  // Assign the passed 'description' value to this object's description variable
    }

    // Getter for the ID field
    // Allows other classes to read the department ID
    public int getId() {
        return id;  // Return the stored department ID
    }
    // Getter for the name field
    // Allows other classes to read the department name
    public String getName() {
        return name;  // Return the stored department name
    }
    // Getter for the description field
    // Allows other classes to read the description text
    public String getDescription() {
        return description;  // Return the stored department description
    }
    // toString() method:
    // This method defines what will be shown when a Department object
    // is printed (for example, System.out.println(dept);).
    // Returning only 'name' makes output cleaner when listing employees.
    @Override
    public String toString() {
        return name;  // Show only the department name when printed
    }
}
