package com.hospital.management;

import com.hospital.management.database.*;
import com.hospital.management.model.*;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("      DATABASE CONNECTION TEST          ");
        System.out.println("========================================");

        // Test 1: Basic Connection
        System.out.println("Test 1: Testing database connection...");
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Connection test passed!");
            DatabaseConnection.closeConnection(connection);
        } else {
            System.out.println("Connection test failed!");
            return;
        }

        System.out.println("========================================");

        // Test 2: Insert Patient
        System.out.println("Test 2: Inserting a patient...");
        Patient patient = new Patient(0, "Test Patient", 30, "O+");
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.insertPatient(patient);

        System.out.println("========================================");

        // Test 3: Read Patients
        System.out.println("Test 3: Reading all patients...");
        patientDAO.displayAllPatients();

        System.out.println("========================================");
        System.out.println("          ALL TESTS COMPLETED!          ");
        System.out.println("========================================");
    }
}