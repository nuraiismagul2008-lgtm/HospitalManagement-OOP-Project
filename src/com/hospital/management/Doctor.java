package com.hospital.management;

public class Doctor {
    // 1. PRIVATE FIELDS (4 fields)
    private int doctorId;
    private String name;
    private String specialization;
    private int experienceYears;

    // 2. CONSTRUCTOR WITH PARAMETERS
    public Doctor(int doctorId, String name, String specialization, int experienceYears) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
    }

    // 3. DEFAULT CONSTRUCTOR (optional)
    public Doctor() {
        this.doctorId = 0;
        this.name = "Unknown";
        this.specialization = "General";
        this.experienceYears = 0;
    }

    // 4. GETTERS (one for each field)
    public int getDoctorId() {
        return doctorId;
    }
    public String getName() {
        return name;
    }
    public String getSpecialization() {
        return specialization;
    }
    public int getExperienceYears() {
        return experienceYears;
    }

    // 5. SETTERS (one for each field)
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    // 6. ADDITIONAL METHODS (minimum 2)

    public boolean isExperienced() {
        return experienceYears > 5;
    }

    public boolean canPerformSurgery() {
        return specialization.equals("Surgeon") ||
                specialization.equals("Cardiology") ||
                specialization.equals("Neurology");
    }

    // 7. toString() METHOD
    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experienceYears=" + experienceYears +
                '}';
    }
}