package com.hospital.management;

public class Nurse extends MedicalStaff {
    // Additional field specific to Nurse
    private int patientsAssigned;

    // Constructor - uses super()
    public Nurse(int staffId, String name, String department, int experienceYears, int patientsAssigned) {
        super(staffId, name, department, experienceYears); // MUST BE FIRST!
        this.patientsAssigned = patientsAssigned;
    }

    // Getter and Setter
    public int getPatientsAssigned() {
        return patientsAssigned;
    }

    // Override method 1: work()
    @Override
    public void work() {
        System.out.println("Nurse " + name + " is caring for " + patientsAssigned + " patients.");
    }

    // Override method 2: getRole()
    @Override
    public String getRole() {
        return "Nurse";
    }

    // Another new method
    public boolean isHeadNurse() {
        return experienceYears >= 8 && patientsAssigned <= 5;
    }

    @Override
    public String toString() {
        return super.toString() + " | Patients Assigned: " + patientsAssigned;
    }
}
