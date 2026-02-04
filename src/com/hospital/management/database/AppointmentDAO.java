package com.hospital.management.database;

import com.hospital.management.model.Appointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // Insert
    public void insertAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointment (patient_name, doctor_name, appointment_date, is_cancelled) VALUES (?, ?, ?, ?)";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, appointment.getPatientName());
            statement.setString(2, appointment.getDoctorName());
            statement.setString(3, appointment.getDate());
            statement.setBoolean(4, appointment.isCancelled());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Appointment inserted successfully!");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    // READ
    public void displayAllAppointments() {
        String sql = "SELECT * FROM appointment ORDER BY appointment_id";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("ALL APPOINTMENTS FROM DATABASE");

            int count = 0;
            while (resultSet.next()) {
                count++;
                int id = resultSet.getInt("appointment_id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                String date = resultSet.getString("appointment_date");
                boolean cancelled = resultSet.getBoolean("is_cancelled");

                System.out.println(count + ". Appointment #" + id);
                System.out.println("   Patient: " + patientName);
                System.out.println("   Doctor: " + doctorName);
                System.out.println("   Date: " + date);
                System.out.println("   Status: " + (cancelled ? "CANCELLED" : "Active"));
                System.out.println();
            }

            if (count == 0) {
                System.out.println("No appointments found in database.");
            } else {
                System.out.println("Total appointments: " + count);
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

    // Get appointment by ID
    public Appointment getAppointmentById(int appointmentId) {
        String sql = "SELECT * FROM appointment WHERE appointment_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, appointmentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("appointment_id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                String date = resultSet.getString("appointment_date");

                resultSet.close();
                statement.close();
                return new Appointment(id, patientName, doctorName, date);
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
    public boolean updateAppointment(Appointment appointment) {
        String sql = "UPDATE appointment SET patient_name = ?, doctor_name = ?, appointment_date = ? WHERE appointment_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, appointment.getPatientName());
            statement.setString(2, appointment.getDoctorName());
            statement.setString(3, appointment.getDate());
            statement.setInt(4, appointment.getAppointmentId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("Appointment updated (ID: " + appointment.getAppointmentId() + ")");
                return true;
            } else {
                System.out.println("No appointment found with ID: " + appointment.getAppointmentId());
            }
        } catch (SQLException e) {
            System.out.println("Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    //Cancel
    public boolean cancelAppointment(int appointmentId) {
        String sql = "UPDATE appointment SET is_cancelled = TRUE WHERE appointment_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, appointmentId);

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("Appointment cancelled (ID: " + appointmentId + ")");
                return true;
            } else {
                System.out.println("No appointment found with ID: " + appointmentId);
            }
        } catch (SQLException e) {
            System.out.println("Cancel failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    //DELETE
    public boolean deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM appointment WHERE appointment_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, appointmentId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println("Appointment deleted (ID: " + appointmentId + ")");
                return true;
            } else {
                System.out.println("No appointment found with ID: " + appointmentId);
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
      // patient name
    public List<Appointment> searchByPatientName(String patientName) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointment WHERE patient_name ILIKE ? ORDER BY appointment_date";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return appointments;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + patientName + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("appointment_id");
                String pName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                String date = resultSet.getString("appointment_date");

                appointments.add(new Appointment(id, pName, doctorName, date));
            }

            resultSet.close();
            statement.close();
            System.out.println("Found " + appointments.size() + " appointment(s)");
        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return appointments;
    }

      // doctor name
    public List<Appointment> searchByDoctorName(String doctorName) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointment WHERE doctor_name ILIKE ? ORDER BY appointment_date";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return appointments;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + doctorName + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("appointment_id");
                String patientName = resultSet.getString("patient_name");
                String dName = resultSet.getString("doctor_name");
                String date = resultSet.getString("appointment_date");

                appointments.add(new Appointment(id, patientName, dName, date));
            }

            resultSet.close();
            statement.close();
            System.out.println("Found " + appointments.size() + " appointment(s)");
        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return appointments;
    }
}
