package com.hospital.management;

public class Nurse extends MedicalStaff {
    // Additional field specific to Nurse
    private int patientsAssigned;

    // Constructor - uses super()
    public Nurse(int staffId, String name, String department, int experienceYears, int patientsAssigned) {
        super(staffId, name, department, experienceYears);
        this.patientsAssigned = patientsAssigned;
    }

    // Getter and Setter
    public int getPatientsAssigned() {
        return patientsAssigned;
    }

    public void setPatientsAssigned(int patientsAssigned) {
        if (patientsAssigned >= 0) {
            this.patientsAssigned = patientsAssigned;
        } else {
            System.out.println("Warning: Patients assigned must be between 0 and 50! Setting to 0.");
            this.patientsAssigned = 0;
        }
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

    // New method specific to Nurse
    public void assistPatient(String patientName) {
        System.out.println("Nurse " + name + " is assisting patient: " + patientName);
    }

    // Another new method
    public void administerMedication(String medication, String patientName) {
        System.out.println("Nurse " + name + " is administering " + medication + " to " + patientName);
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
