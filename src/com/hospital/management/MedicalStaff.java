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
