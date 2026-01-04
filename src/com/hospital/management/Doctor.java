package com.hospital.management;

public class Doctor extends MedicalStaff {
    // Additional field specific to Doctor
    private final String specialization;

    // Constructor - uses super() to call parent constructor
    public Doctor(int staffId, String name, String department, int experienceYears, String specialization) {
        super(staffId, name, department, experienceYears >= 0 ? experienceYears : 0);
        this.specialization = specialization;
    }

    // Getter and Setter for new field
    public String getSpecialization() {
        return specialization;
    }

    // Override method 1: work()
    @Override
    public void work() {
        System.out.println("Dr. " + name + " is treating patients in " + specialization + " department.");
    }

    // Override method 2: getRole()
    @Override
    public String getRole() {
        return "Doctor";
    }

    // Another new method
    public boolean canPerformSurgery() {
        return specialization.equals("Surgeon") ||
                specialization.equals("Cardiology") ||
                specialization.equals("Neurology");
    }

    // Another new method
    public boolean isSpecialist() {
        return experienceYears >= 10;
    }

    @Override
    public String toString() {
        return super.toString() + " | Specialization: " + specialization;
    }
}