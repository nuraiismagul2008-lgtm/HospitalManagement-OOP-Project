package com.hospital.management;

public class Doctor extends MedicalStaff {
    // Additional field specific to Doctor
    private String specialization;

    // Constructor - uses super() to call parent constructor
    public Doctor(int staffId, String name, String department, int experienceYears, String specialization) {
        super(staffId, name, department, experienceYears); // MUST BE FIRST!
        this.specialization = specialization;
    }

    // Getter and Setter for new field
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (specialization != null && !specialization.trim().isEmpty()) {
            this.specialization = specialization;
        } else {
            System.out.println("Warning: Specialization cannot be empty!");
        }
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

    // New method specific to Doctor
    public void diagnosePatient(String patientName) {
        System.out.println("Dr. " + name + " is diagnosing patient: " + patientName);
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