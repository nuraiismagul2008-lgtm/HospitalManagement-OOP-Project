package com.hospital.management;

public class MedicalStaff {
    // Protected fields - accessible in child classes
    protected int staffId;
    protected String name;
    protected String department;
    protected int experienceYears;

    // Constructor
    public MedicalStaff(int staffId, String name, String department, int experienceYears) {
        this.staffId = staffId;
        this.name = name;
        this.department = department;
        this.experienceYears = experienceYears;
    }

    // Getters
    public int getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    // Setters with validation
    public void setStaffId(int staffId) {
        if (staffId > 0) {
            this.staffId = staffId;
        } else {
            System.out.println("Warning: Staff ID must be positive!");
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Warning: Name cannot be empty!");
        }
    }

    public void setDepartment(String department) {
        if (department != null && !department.trim().isEmpty()) {
            this.department = department;
        }
    }

    public void setExperienceYears(int experienceYears) {
        if (experienceYears >= 0) {
            this.experienceYears = experienceYears;
        }
    }

    // Method to be overridden - describes what staff member does
    public void work() {
        System.out.println(name + " is working in the hospital.");
    }

    // Another method to be overridden - returns role
    public String getRole() {
        return "Medical Staff";
    }

    // Method that won't be overridden - checks experience level
    public boolean isExperienced() {
        return experienceYears >= 5;
    }

    @Override
    public String toString() {
        return "[" + getRole() + "] " + name +
                " (ID: " + staffId +
                ", Dept: " + department +
                ", Experience: " + experienceYears + " years)";
    }
}
