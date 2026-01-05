package com.hospital.management;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // ArrayLists to store all objects
    private static final ArrayList<Patient> patients = new ArrayList<>();
    private static final ArrayList<Appointment> appointments = new ArrayList<>();
    private static final ArrayList<MedicalStaff> medicalStaff = new ArrayList<>();
    // Scanner for reading user input
    private static final Scanner scanner = new Scanner(System.in);

    static void main() {
        System.out.println("---------------==◎◉◎==---------------");
        System.out.println("Welcome to Hospital Management System");
        System.out.println("---------------==◎◉◎==---------------");
        System.out.println();

        // initial test data
        addTestData();

        // Menu loop
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    viewAllPatients();
                    break;
                case 3:
                    addDoctor();
                    break;
                case 4:
                    viewDoctorsOnly();
                    break;
                case 5:
                    addNurse();
                    break;
                case 6:
                    viewNursesOnly();
                    break;
                case 7:
                    addAppointment();
                    break;
                case 8:
                    viewAllAppointments();
                    break;
                case 9:
                    addMedicalStaff();
                    break;
                case 10:
                    viewAllMedicalStaff();
                    break;
                case 11:
                    demonstratePolymorphism();
                    break;
                case 0:
                    System.out.println("Goodbye! Thank you for using Hospital Management System!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            if (running) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    // Add initial test data
    private static void addTestData() {
        patients.add(new Patient(1001, "Aidar Bekzhan", 25, "O+"));
        patients.add(new Patient(1002, "Nurfatima Zulpyhar", 15, "A+"));
        patients.add(new Patient(1003, "Yerlan Sadykov", 70, "B-"));

        appointments.add(new Appointment(3001, "Aidar Bekzhan", "Dr. Samat Aliyev", "2025-01-15"));
        appointments.add(new Appointment(3002, "Asel Nurgaliyeva", "Dr. Dinara Kassymova", "2025-01-16"));

        medicalStaff.add(new MedicalStaff(5001, "Aibek Nurlan", "Administration", 5));
        medicalStaff.add(new Nurse(5002, "Aigul Bekova", "Emergency", 6, 8));
        medicalStaff.add(new Nurse(5003, "Madina Omarova", "ICU", 10, 4));
    }

    // Display main menu
    private static void displayMenu() {
        System.out.println("========================================");
        System.out.println("     HOSPITAL MANAGEMENT SYSTEM         ");
        System.out.println("========================================");
        System.out.println("1. Add Patient");
        System.out.println("2. View All Patients");
        System.out.println("3. Add Doctor");
        System.out.println("4. View All Doctors");
        System.out.println("5. Add Appointment");
        System.out.println("6. View All Appointments");
        System.out.println("7. Add Medical Staff (General)");
        System.out.println("8. Add Nurse");
        System.out.println("9. View All Medical Staff (Polymorphic)");
        System.out.println("10. Make All Staff Work (Polymorphism Demo)");
        System.out.println("11. View Doctors Only");
        System.out.println("12. View Nurses Only");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    // Add new patient
    private static void addPatient() {
        System.out.println("--- ADD PATIENT ---");

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
    }

    // View all patients
    private static void viewAllPatients() {
        System.out.println("========================================");
        System.out.println("               ALL PATIENTS             ");
        System.out.println("========================================");

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("Total patients: " + patients.size());
        System.out.println();

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

    // Add new doctor
    private static void addDoctor() {
        System.out.println("--- ADD DOCTOR ---");

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

        MedicalStaff staff = new Doctor(id,name, department, experience,specialization);
        medicalStaff.add(staff);
        System.out.println("Doctor added successfully!");
    }

    // Add new appointment
    private static void addAppointment() {
        System.out.println("--- ADD APPOINTMENT ---");

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
    }

    // View all appointments
    private static void viewAllAppointments() {
        System.out.println("========================================");
        System.out.println("             ALL APPOINTMENTS           ");
        System.out.println("========================================");

        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }

        System.out.println("Total appointments: " + appointments.size());
        System.out.println();

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

    // Add General Medical Staff
    private static void addMedicalStaff() {
        System.out.println("--- ADD MEDICAL STAFF (GENERAL) ---");

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

        MedicalStaff staff = new MedicalStaff(id, name, department, experience);
        medicalStaff.add(staff);

        System.out.println("Medical staff member added successfully!");
    }

    // Add Nurse
    private static void addNurse() {
        System.out.println("--- ADD NURSE ---");

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

        // Polymorphism: Store Nurse as MedicalStaff
        MedicalStaff staff = new Nurse(id, name, department, experience, patients);
        medicalStaff.add(staff);

        System.out.println("Nurse added successfully!");
    }

    // View All Medical Staff (Polymorphic)
    private static void viewAllMedicalStaff() {
        System.out.println("========================================");
        System.out.println("     ALL MEDICAL STAFF (POLYMORPHIC)    ");
        System.out.println("========================================");

        if (medicalStaff.isEmpty()) {
            System.out.println("No medical staff found.");
            return;
        }

        System.out.println("Total staff: " + medicalStaff.size());
        System.out.println();

        for (int i = 0; i < medicalStaff.size(); i++) {
            MedicalStaff staff = medicalStaff.get(i);
            System.out.println((i + 1) + ". " + staff); // Calls overridden toString()

            // Use instanceof to show child-specific badges
            if (staff instanceof Doctor doctor) {
                if (doctor.isSpecialist()) {
                    System.out.println("Specialist Doctor");
                }
                if (doctor.canPerformSurgery()) {
                    System.out.println("Can perform surgery");
                }
            } else if (staff instanceof Nurse nurse) {

                if (nurse.isHeadNurse()) {
                    System.out.println("Head Nurse");
                }
            }

            System.out.println();
        }
    }

    // Demonstrate Polymorphism
    private static void demonstratePolymorphism() {
        System.out.println("========================================");
        System.out.println("       POLYMORPHISM DEMONSTRATION       ");
        System.out.println("========================================");
        System.out.println("Calling work() on all staff members:");

        for (MedicalStaff staff : medicalStaff) {
            staff.work();
        }
    }

    // View Doctors Only
    private static void viewDoctorsOnly() {
        System.out.println("========================================");
        System.out.println("              DOCTORS ONLY              ");
        System.out.println("========================================");

        int doctorCount = 0;

        for (MedicalStaff staff : medicalStaff) {
            if (staff instanceof Doctor doctor) { // Filter by type
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

    // View Nurses Only
    private static void viewNursesOnly() {
        System.out.println("========================================");
        System.out.println("               NURSES ONLY              ");
        System.out.println("========================================");

        int nurseCount = 0;

        for (MedicalStaff staff : medicalStaff) {
            if (staff instanceof Nurse nurse) { // Filter by type
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