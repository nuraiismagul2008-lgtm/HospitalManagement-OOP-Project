package com.hospital.management.menu;

import com.hospital.management.model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuManagment implements Menu {
    private final ArrayList<Patient> patients;
    private final ArrayList<Appointment> appointments;
    private final ArrayList<MedicalStaff> medicalStaff;
    private final Scanner scanner;

    public MenuManagment() {
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.medicalStaff = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        addTestData();
    }

    private void addTestData() {
        patients.add(new Patient(1001, "Aidar Bekzhan", 25, "O+"));
        patients.add(new Patient(1002, "Nurfatima Zulpyhar", 15, "A+"));
        patients.add(new Patient(1003, "Yerlan Sadykov", 70, "B-"));

        appointments.add(new Appointment(3001, "Aidar Bekzhan", "Dr. Samat Aliyev", "2025-01-15"));
        appointments.add(new Appointment(3002, "Asel Nurgaliyeva", "Dr. Dinara Kassymova", "2025-01-16"));

        medicalStaff.add(new Doctor(5001, "Samat Aliyev", "Cardiology", 10, "Cardiology"));
        medicalStaff.add(new Nurse(5002, "Aigul Bekova", "Emergency", 6, 8));
        medicalStaff.add(new Nurse(5003, "Madina Omarova", "ICU", 10, 4));
    }

    @Override
    public void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("     HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Patient");
        System.out.println("2. View All Patients");
        System.out.println("3. Add Doctor");
        System.out.println("4. View Doctors Only");
        System.out.println("5. Add Nurse");
        System.out.println("6. View Nurses Only");
        System.out.println("7. Add Appointment");
        System.out.println("8. View All Appointments");
        System.out.println("9. View All Medical Staff (Polymorphic)");
        System.out.println("10. Make All Staff Work (Polymorphism Demo)");
        System.out.println("11. Demonstrate Treatment (Interface Demo)");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void run() {
        System.out.println("---------------==◎◉◎==---------------");
        System.out.println("Welcome to Hospital Management System");
        System.out.println("---------------==◎◉◎==---------------\n");

        boolean running = true;
        while (running) {
            try {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addPatient();
                    case 2 -> viewAllPatients();
                    case 3 -> addDoctor();
                    case 4 -> viewDoctorsOnly();
                    case 5 -> addNurse();
                    case 6 -> viewNursesOnly();
                    case 7 -> addAppointment();
                    case 8 -> viewAllAppointments();
                    case 9 -> viewAllMedicalStaff();
                    case 10 -> demonstratePolymorphism();
                    case 11 -> demonstrateTreatment();
                    case 0 -> {
                        System.out.println("\nGoodbye! Thank you for using Hospital Management System!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice! Please try again.");
                }

                if (running) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private void addPatient() {
        try {
            System.out.println("\n--- ADD PATIENT ---");
            System.out.print("Enter patient ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter full name: ");
            String name = scanner.nextLine();

            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter blood type (A+, A-, B+, B-, AB+, AB-, O+, O-): ");
            String bloodType = scanner.nextLine();

            Patient patient = new Patient(id, name, age, bloodType);
            patients.add(patient);
            System.out.println("Patient added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding patient: " + e.getMessage());
        }
    }

    private void viewAllPatients() {
        System.out.println("\n========================================");
        System.out.println("          ALL PATIENTS");
        System.out.println("========================================");

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("Total patients: " + patients.size() + "\n");

        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i);
            System.out.println((i + 1) + ". " + p.getFullName());
            System.out.println("   ID: " + p.getPatientId());
            System.out.println("   Age: " + p.getAge() + " years");
            System.out.println("   Blood Type: " + p.getBloodType());
            System.out.println("   Category: " + p.getAgeCategory());
            System.out.println("   Minor: " + (p.isMinor() ? "Yes" : "No"));
            System.out.println();
        }
    }

    private void addDoctor() {
        try {
            System.out.println("\n--- ADD DOCTOR ---");
            System.out.print("Enter doctor ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter doctor name: ");
            String name = scanner.nextLine();

            System.out.print("Enter department: ");
            String department = scanner.nextLine();

            System.out.print("Enter years of experience: ");
            int experience = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter specialization: ");
            String specialization = scanner.nextLine();

            MedicalStaff staff = new Doctor(id, name, department, experience, specialization);
            medicalStaff.add(staff);
            System.out.println("Doctor added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding doctor: " + e.getMessage());
        }
    }

    private void addNurse() {
        try {
            System.out.println("\n--- ADD NURSE ---");
            System.out.print("Enter staff ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter department: ");
            String department = scanner.nextLine();

            System.out.print("Enter years of experience: ");
            int experience = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter number of patients assigned: ");
            int patients = scanner.nextInt();
            scanner.nextLine();

            MedicalStaff staff = new Nurse(id, name, department, experience, patients);
            medicalStaff.add(staff);
            System.out.println("Nurse added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding nurse: " + e.getMessage());
        }
    }

    private void addAppointment() {
        try {
            System.out.println("\n--- ADD APPOINTMENT ---");
            System.out.print("Enter appointment ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter patient name: ");
            String patientName = scanner.nextLine();

            System.out.print("Enter doctor name: ");
            String doctorName = scanner.nextLine();

            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            Appointment appointment = new Appointment(id, patientName, doctorName, date);
            appointments.add(appointment);
            System.out.println("Appointment added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding appointment: " + e.getMessage());
        }
    }

    private void viewAllAppointments() {
        System.out.println("\n========================================");
        System.out.println("          ALL APPOINTMENTS");
        System.out.println("========================================");

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        System.out.println("Total appointments: " + appointments.size() + "\n");

        for (int i = 0; i < appointments.size(); i++) {
            Appointment a = appointments.get(i);
            System.out.println((i + 1) + ". Appointment #" + a.getAppointmentId());
            System.out.println("   Patient: " + a.getPatientName());
            System.out.println("   Doctor: " + a.getDoctorName());
            System.out.println("   Date: " + a.getDate());
            System.out.println("   Status: " + (a.isCancelled() ? "CANCELLED" : "Active"));
            System.out.println();
        }
    }

    private void viewAllMedicalStaff() {
        System.out.println("\n========================================");
        System.out.println("   ALL MEDICAL STAFF (POLYMORPHIC)");
        System.out.println("========================================");

        if (medicalStaff.isEmpty()) {
            System.out.println("No medical staff found.");
            return;
        }

        System.out.println("Total staff: " + medicalStaff.size() + "\n");

        for (int i = 0; i < medicalStaff.size(); i++) {
            MedicalStaff staff = medicalStaff.get(i);
            System.out.println((i + 1) + ". " + staff);

            if (staff instanceof Doctor doctor) {
                if (doctor.isSpecialist()) {
                    System.out.println("   [STATUS] Specialist Doctor");
                }
                if (doctor.canPerformSurgery()) {
                    System.out.println("   [CAPABILITY] Can perform surgery");
                }
            } else if (staff instanceof Nurse nurse) {
                if (nurse.isHeadNurse()) {
                    System.out.println("   [STATUS] Head Nurse");
                }
            }
            System.out.println();
        }
    }

    private void demonstratePolymorphism() {
        System.out.println("\n========================================");
        System.out.println("     POLYMORPHISM DEMONSTRATION");
        System.out.println("========================================");
        System.out.println("Calling work() on all staff members:\n");

        for (MedicalStaff staff : medicalStaff) {
            staff.work();
        }

        System.out.println("\n[NOTICE] Same method name (work()), different output!");
        System.out.println("         This is POLYMORPHISM in action!");
    }

    private void demonstrateTreatment() {
        System.out.println("\n========================================");
        System.out.println("     INTERFACE DEMONSTRATION");
        System.out.println("========================================");
        System.out.println("Calling treat() on all Treatable staff:\n");

        for (MedicalStaff staff : medicalStaff) {
            if (staff instanceof Treatable treatable) {
                treatable.treat("John Doe");
                System.out.println("Treatment type: " + treatable.getTreatmentType());
                System.out.println();
            }
        }
    }

    private void viewDoctorsOnly() {
        System.out.println("\n========================================");
        System.out.println("          DOCTORS ONLY");
        System.out.println("========================================");

        int doctorCount = 0;
        for (MedicalStaff staff : medicalStaff) {
            if (staff instanceof Doctor doctor) {
                doctorCount++;
                System.out.println(doctorCount + ". " + doctor.getName());
                System.out.println("   Department: " + doctor.getDepartment());
                System.out.println("   Specialization: " + doctor.getSpecialization());
                System.out.println("   Experience: " + doctor.getExperienceYears() + " years");

                if (doctor.isSpecialist()) {
                    System.out.println("   Status: Specialist");
                }
                if (doctor.canPerformSurgery()) {
                    System.out.println("   Capability: Can perform surgery");
                }
                System.out.println();
            }
        }

        if (doctorCount == 0) {
            System.out.println("No doctors found.");
        }
    }

    private void viewNursesOnly() {
        System.out.println("\n========================================");
        System.out.println("          NURSES ONLY");
        System.out.println("========================================");

        int nurseCount = 0;
        for (MedicalStaff staff : medicalStaff) {
            if (staff instanceof Nurse nurse) {
                nurseCount++;
                System.out.println(nurseCount + ". " + nurse.getName());
                System.out.println("   Department: " + nurse.getDepartment());
                System.out.println("   Patients Assigned: " + nurse.getPatientsAssigned());
                System.out.println("   Experience: " + nurse.getExperienceYears() + " years");

                if (nurse.isHeadNurse()) {
                    System.out.println("   Status: Head Nurse");
                }
                System.out.println();
            }
        }

        if (nurseCount == 0) {
            System.out.println("No nurses found.");
        }
    }
}