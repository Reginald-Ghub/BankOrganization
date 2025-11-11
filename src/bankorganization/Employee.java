/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankorganization;

/**
 *
 * @author USER
 */
public class Employee {

// Represents an employee in the bank

    private final int id;                  // Unique ID
    private String name;             // Employee name
    private Manager manager;         // Manager assigned
    private Department department;   // Department assigned
    // Constructor
    public Employee(int id, String name, Manager manager, Department department) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.department = department;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public Manager getManager() { return manager; }
    public Department getDepartment() { return department; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setManager(Manager manager) { this.manager = manager; }
    public void setDepartment(Department department) { this.department = department; }

    @Override
    public String toString() {
        return String.format("Employee ID: %d | Name: %s | Manager: %s | Department: %s",
                id, name, manager.getTitle(), department.getName());
    }
}
