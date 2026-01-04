package com.hospital.management;

public class Doctor {
    // 1. PRIVATE FIELDS (4 fields)
    private int doctorId;
    private String name;
    private String specialization;
    private int experienceYears;

    // 2. CONSTRUCTOR WITH PARAMETERS
    public Doctor(int doctorId, String name, String specialization, int experienceYears) {
        setDoctorId(doctorId);
        setName(name);
        setSpecialization(specialization);
        setExperienceYears(experienceYears);
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

    // 5. SETTERS (one for each field) - WITH VALIDATION

    public void setDoctorId(int doctorId) {
        if (doctorId > 0) {
            this.doctorId = doctorId;
        } else {
            System.out.println("Warning: Doctor ID must be positive! Setting to 0.");
            this.doctorId = 0;
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Warning: Name cannot be empty! Keeping current value.");
        }
    }

    public void setSpecialization(String specialization) {
        if (specialization != null && !specialization.trim().isEmpty()) {
            this.specialization = specialization;
        } else {
            System.out.println("Warning: Specialization cannot be empty! Setting to 'General'.");
            this.specialization = "General";
        }
    }

    public void setExperienceYears(int experienceYears) {
        if (experienceYears >= 0 && experienceYears <= 60) {
            this.experienceYears = experienceYears;
        } else {
            System.out.println("Warning: Experience years must be between 0 and 60! Setting to 0.");
            this.experienceYears = 0;
        }
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