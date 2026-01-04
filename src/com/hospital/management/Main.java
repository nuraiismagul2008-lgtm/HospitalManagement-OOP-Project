package com.hospital.management;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // ArrayLists to store all objects
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    // Scanner for reading user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("---------------==◎◉◎==---------------");
        System.out.println("Welcome to Hospital Management System");
        System.out.println("---------------==◎◉◎==---------------");
        System.out.println();

        // Add initial test data
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
                    viewAllDoctors();
                    break;
                case 5:
                    addAppointment();
                    break;
                case 6:
                    viewAllAppointments();
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

        doctors.add(new Doctor(2001, "Dr. Samat Aliyev", "Cardiology", 10));
        doctors.add(new Doctor(2002, "Dr. Dinara Kassymova", "Pediatrics", 3));

        appointments.add(new Appointment(3001, "Aidar Bekzhan", "Dr. Samat Aliyev", "2025-01-15"));
        appointments.add(new Appointment(3002, "Asel Nurgaliyeva", "Dr. Dinara Kassymova", "2025-01-16"));
    }

    // Display main menu
    private static void displayMenu() {
        System.out.println("========================================");
        System.out.println("        HOSPITAL MANAGEMENT SYSTEM      ");
        System.out.println("========================================");
        System.out.println("1. Add Patient");
        System.out.println("2. View All Patients");
        System.out.println("3. Add Doctor");
        System.out.println("4. View All Doctors");
        System.out.println("5. Add Appointment");
        System.out.println("6. View All Appointments");
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
        System.out.println("             ALL PATIENTS               ");
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

        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter years of experience: ");
        int experience = scanner.nextInt();
        scanner.nextLine();

        Doctor doctor = new Doctor(id, name, specialization, experience);
        doctors.add(doctor);

        System.out.println("Doctor added successfully!");
    }

    // View all doctors
    private static void viewAllDoctors() {
        System.out.println("========================================");
        System.out.println("              ALL DOCTORS               ");
        System.out.println("========================================");

        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        System.out.println("Total doctors: " + doctors.size());
        System.out.println();

        for (int i = 0; i < doctors.size(); i++) {
            Doctor d = doctors.get(i);
            System.out.println((i + 1) + ". " + d.getName());
            System.out.println("   ID: " + d.getDoctorId());
            System.out.println("   Specialization: " + d.getSpecialization());
            System.out.println("   Experience: " + d.getExperienceYears() + " years");
            System.out.println("   Experienced: " + (d.isExperienced() ? "Yes" : "No"));
            System.out.println("   Can Perform Surgery: " + (d.canPerformSurgery() ? "Yes" : "No"));
            System.out.println();
        }
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
        System.out.println("            ALL APPOINTMENTS            ");
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
}