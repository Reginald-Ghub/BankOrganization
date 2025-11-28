/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankorganization;

/**
 *
 * @author USER
 */
public class Employee { // This class models a bank employee with an ID, name, assigned manager, and department.

    private final int id;  // Unique identifier for the employee. Once set, it cannot be changed.
    private String name;   // Stores the employee's full name. Can be updated later.
    private Manager manager;   // Stores the Manager object assigned to this employee (e.g., HeadManager, TeamLead)
    private Department department;   // Stores the Department object assigned to this employee (e.g., HR, Finance)
    
    // Constructor: Called when creating a new Employee object.
    // Assigns the provided ID, name, manager, and department to this employee.
    public Employee(int id, String name, Manager manager, Department department) {
        this.id = id;  // Set the employee's unique ID
        this.name = name;  // Set the employee's name
        this.manager = manager;  // Set the employee's assigned manager
        this.department = department;  // Set the employee's assigned department
    }

    // Getter Methods
    // These methods allow other classes to read the employee's information.
    public int getId() { return id; }  // Return the employee's unique ID
    public String getName() { return name; }  // Return the employee's name
    public Manager getManager() { return manager; }  // Return the manager assigned to the employee
    public Department getDepartment() { return department; }  // Return the department assigned to the employee

    // Setter Methods
    // These methods allow other classes to update the employee's name, manager, or department.
    public void setName(String name) { this.name = name; }  // Update the employee's name
    public void setManager(Manager manager) { this.manager = manager; }  // Update the employee's manager
    public void setDepartment(Department department) { this.department = department; }  // Update the employee's department

    // toString() Method
    // This defines how the Employee object is displayed when printed.
    // It shows ID, Name, Manager's title, and Department name in a readable format.
    @Override
    public String toString() {
        return String.format("Employee ID: %d | Name: %s | Manager: %s | Department: %s",
                id, name, manager.getTitle(), department.getName());
    }
}
