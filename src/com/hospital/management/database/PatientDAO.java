package com.hospital.management.database;

import com.hospital.management.model.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    //CREATE
    public void insertPatient(Patient patient) {
        String sql = "INSERT INTO patient (full_name, age, blood_type) VALUES (?, ?, ?)";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patient.getFullName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getBloodType());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Patient inserted successfully!");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    //READ
    public void displayAllPatients() {
        String sql = "SELECT * FROM patient ORDER BY patient_id";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("     ALL PATIENTS FROM DATABASE");

            int count = 0;
            while (resultSet.next()) {
                count++;
                int id = resultSet.getInt("patient_id");
                String name = resultSet.getString("full_name");
                int age = resultSet.getInt("age");
                String bloodType = resultSet.getString("blood_type");

                System.out.println(count + ". " + name);
                System.out.println("   ID: " + id);
                System.out.println("   Age: " + age + " years");
                System.out.println("   Blood Type: " + bloodType);
                System.out.println();
            }

            if (count == 0) {
                System.out.println("No patients found in database.");
            } else {
                System.out.println("Total patients: " + count);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    // Get patient by ID
    public Patient getPatientById(int patientId) {
        String sql = "SELECT * FROM patient WHERE patient_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("patient_id");
                String name = resultSet.getString("full_name");
                int age = resultSet.getInt("age");
                String bloodType = resultSet.getString("blood_type");

                resultSet.close();
                statement.close();
                return new Patient(id, name, age, bloodType);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    //UPDATE
    public boolean updatePatient(Patient patient) {
        String sql = "UPDATE patient SET full_name = ?, age = ?, blood_type = ? WHERE patient_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patient.getFullName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getBloodType());
            statement.setInt(4, patient.getPatientId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("Patient updated: " + patient.getFullName());
                return true;
            } else {
                System.out.println("No patient found with ID: " + patient.getPatientId());
            }
        } catch (SQLException e) {
            System.out.println("Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    //DELETE
    public boolean deletePatient(int patientId) {
        String sql = "DELETE FROM patient WHERE patient_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, patientId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println("Patient deleted (ID: " + patientId + ")");
                return true;
            } else {
                System.out.println("No patient found with ID: " + patientId);
            }
        } catch (SQLException e) {
            System.out.println("Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    //SEARCH
    //name
    public List<Patient> searchByName(String name) {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient WHERE full_name ILIKE ? ORDER BY full_name";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return patients;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");  // Add wildcards
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("patient_id");
                String fullName = resultSet.getString("full_name");
                int age = resultSet.getInt("age");
                String bloodType = resultSet.getString("blood_type");

                patients.add(new Patient(id, fullName, age, bloodType));
            }

            resultSet.close();
            statement.close();
            System.out.println("Found " + patients.size() + " patient(s)");
        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return patients;
    }

    //age range
    public List<Patient> searchByAgeRange(int minAge, int maxAge) {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient WHERE age BETWEEN ? AND ? ORDER BY age";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return patients;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minAge);
            statement.setInt(2, maxAge);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("patient_id");
                String fullName = resultSet.getString("full_name");
                int age = resultSet.getInt("age");
                String bloodType = resultSet.getString("blood_type");

                patients.add(new Patient(id, fullName, age, bloodType));
            }

            resultSet.close();
            statement.close();
            System.out.println("Found " + patients.size() + " patient(s)");
        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return patients;
    }

    //blood type
    public List<Patient> searchByBloodType(String bloodType) {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient WHERE blood_type = ? ORDER BY full_name";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return patients;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, bloodType);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("patient_id");
                String fullName = resultSet.getString("full_name");
                int age = resultSet.getInt("age");
                String bt = resultSet.getString("blood_type");

                patients.add(new Patient(id, fullName, age, bt));
            }

            resultSet.close();
            statement.close();
            System.out.println("Found " + patients.size() + " patient(s) with blood type " + bloodType);
        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return patients;
    }
}