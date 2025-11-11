/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankorganization;

/**
 *
 * @author USER
 */

// Abstract parent class for manager roles
public abstract class Manager {
    protected int id;       // Unique manager ID
    protected String title; // Title (Head Manager, etc.)
    protected String name;  // Manager's name
    protected int level;    // Level (higher = more senior)

    // Constructor
    public Manager(int id, String title, String name, int level) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.level = level;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getName() { return name; }
    public int getLevel() { return level; }

    @Override
    public String toString() { return title; }
}

// Concrete subclasses
class HeadManager extends Manager {
    public HeadManager(int id, String name) { super(id, "Head Manager", name, 3); }
}

class AssistantManager extends Manager {
    public AssistantManager(int id, String name) { super(id, "Assistant Manager", name, 2); }
}

class TeamLead extends Manager {
    public TeamLead(int id, String name) { super(id, "Team Lead", name, 1); }
}
