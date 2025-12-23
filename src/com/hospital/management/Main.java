package com.hospital.management;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------==◎◉◎==---------------");
        System.out.println("Welcome to Hospital Management System");
        System.out.println("---------------==◎◉◎==---------------");
        System.out.println();
        System.out.println("This system will help manage hospital operations");
        System.out.println("Features:");
        System.out.println("1. Patient Registration");
        System.out.println("2. Doctor Scheduling");
        System.out.println("3. Appointment Booking");

        System.out.println();
        System.out.println("Course: Object-Oriented Programming - Week 1-3");


        // Step 2: Create objects
        // Create Patient objects
        Patient patient1 = new Patient(1001, "Nurfatima Zulpyhar", 25, "O+");
        Patient patient2 = new Patient(1002, "Asel Nurgalieva", 15, "A+");
        Patient patient3 = new Patient();

        // Create Doctor objects
        Doctor doctor1 = new Doctor(2001, "Dr. Kudaibergen", "Cardiology", 10);
        Doctor doctor2 = new Doctor(2002, "Dr. Kassymova", "Pediatrics", 3);

        // Create Appointment objects
        Appointment appointment1 = new Appointment(3001, "Nurfatima Zulpyhar", "Dr. Kudaibergen", "2025-01-15");
        Appointment appointment2 = new Appointment(3002, "Asel Nurgaliyeva", "Dr. Kassymova", "2025-01-16");

        // Step 3: Display all objects
        System.out.println("--- PATIENTS ---");
        System.out.println(patient1);
        System.out.println(patient2);
        System.out.println();

        System.out.println("--- DOCTORS ---");
        System.out.println(doctor1);
        System.out.println(doctor2);
        System.out.println();

        System.out.println("--- APPOINTMENTS ---");
        System.out.println(appointment1);
        System.out.println(appointment2);
        System.out.println();

        // Step 4: Test getters
        System.out.println("--- TESTING GETTERS ---");
        System.out.println("Patient 1 name: " + patient1.getFullName());
        System.out.println("Patient 1 blood type: " + patient1.getBloodType());
        System.out.println("Doctor 1 specialization: " + doctor1.getSpecialization());
        System.out.println("Appointment 1 date: " + appointment1.getDate());
        System.out.println();

        // Step 5: Test setters
        System.out.println("--- TESTING SETTERS ---");
        System.out.println("Updating patient3...");
        patient3.setPatientId(1003);
        patient3.setFullName("Yerlan Sadykov");
        patient3.setAge(70);
        patient3.setBloodType("B-");
        System.out.println("Updated: " + patient3);
        System.out.println();

        System.out.println("Changing appointment2 date...");
        appointment2.setDate("2025-12-29");
        System.out.println("Updated: " + appointment2);
        System.out.println();

        // Step 6: Test additional methods

        // Test Patient methods
        System.out.println("--- TESTING PATIENT METHODS ---");
        System.out.println(patient1.getFullName() + " is minor: " + patient1.isMinor());
        System.out.println(patient2.getFullName() + " is minor: " + patient2.isMinor());
        System.out.println(patient1.getFullName() + " age category: " + patient1.getAgeCategory());
        System.out.println(patient3.getFullName() + " age category: " + patient3.getAgeCategory());
        System.out.println();

        // Test Doctor methods
        System.out.println("--- TESTING DOCTOR METHODS ---");
        System.out.println(doctor1.getName() + " is experienced: " + doctor1.isExperienced());
        System.out.println(doctor2.getName() + " is experienced: " + doctor2.isExperienced());
        System.out.println(doctor1.getName() + " can perform surgery: " + doctor1.canPerformSurgery());
        System.out.println(doctor2.getName() + " can perform surgery: " + doctor2.canPerformSurgery());
        System.out.println();

        // Test Appointment methods
        System.out.println("--- TESTING APPOINTMENT METHODS ---");
        System.out.println("Rescheduling appointment " + appointment1.getAppointmentId());
        appointment1.reschedule("2025-01-18");
        System.out.println("New date: " + appointment1.getDate());
        System.out.println();

        System.out.println("Cancelling appointment " + appointment2.getAppointmentId());
        appointment2.cancel();
        System.out.println("Is cancelled: " + appointment2.isCancelled());
        System.out.println();

        // Step 7: Final state
        System.out.println("--- FINAL STATE ---");
        System.out.println("Patients:");
        System.out.println(patient1);
        System.out.println(patient2);
        System.out.println(patient3);
        System.out.println();

        System.out.println("Doctors:");
        System.out.println(doctor1);
        System.out.println(doctor2);
        System.out.println();

        System.out.println("Appointments:");
        System.out.println(appointment1);
        System.out.println(appointment2);

        System.out.println("-------------==◎◉◎==--------------");
        System.out.println("---------Program Complete---------");
        System.out.println("-------------==◎◉◎==--------------");
        }
        }