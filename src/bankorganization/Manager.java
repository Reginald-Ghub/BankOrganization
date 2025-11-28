/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankorganization;

/**
 *
 * @author USER
 */

// Abstract parent class representing a Manager in the bank
// Defines common attributes and methods shared by all manager types
public abstract class Manager {
    protected int id;       // Unique manager ID
    protected String title; // Title (Head Manager, etc.)
    protected String name;  // Manager's name
    protected int level;    // Level (higher = more senior)

    // Constructor to initialize a manager object
    // Called by subclasses using 'super(...)'
    public Manager(int id, String title, String name, int level) {
        this.id = id;  // Assign unique ID
        this.title = title;  // Assign title
        this.name = name;  // Assign manager name
        this.level = level;  // Assign hierarchy level
    }

    // Getter methods to access private/protected fields
    public int getId() { return id; }  // Return manager ID
    public String getTitle() { return title; }  // Return manager title
    public String getName() { return name; }  // Return manager name
    public int getLevel() { return level; }  // Return manager hierarchy level

    // Override toString() to return manager title for display
    // Useful when printing manager objects
    @Override
    public String toString() { return title; }
}

// Concrete subclass representing a Head Manager
// Always has level 3 (highest in this hierarchy)
class HeadManager extends Manager {
    public HeadManager(int id, String name) { super(id, "Head Manager", name, 3); }
}
// Concrete subclass representing an Assistant Manager
// Level 2 (mid-level manager)
class AssistantManager extends Manager {
    public AssistantManager(int id, String name) { super(id, "Assistant Manager", name, 2); }
}
// Concrete subclass representing a Team Lead
// Level 1 (entry-level manager)
class TeamLead extends Manager {
    public TeamLead(int id, String name) { super(id, "Team Lead", name, 1); }
}
