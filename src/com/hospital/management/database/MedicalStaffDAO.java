package com.hospital.management.database;

import com.hospital.management.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalStaffDAO {

    // CREATE OPERATIONS

    public void insertDoctor(Doctor doctor) {
        String sql = "INSERT INTO medical_staff (name, department, experience_years, staff_type, specialization) " +
                "VALUES (?, ?, ?, 'DOCTOR', ?)";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getDepartment());
            statement.setInt(3, doctor.getExperienceYears());
            statement.setString(4, doctor.getSpecialization());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Doctor inserted successfully!");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Insert failed.");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public void insertNurse(Nurse nurse) {
        String sql = "INSERT INTO medical_staff (name, department, experience_years, staff_type, patients_assigned) " +
                "VALUES (?, ?, ?, 'NURSE', ?)";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nurse.getName());
            statement.setString(2, nurse.getDepartment());
            statement.setInt(3, nurse.getExperienceYears());
            statement.setInt(4, nurse.getPatientsAssigned());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Nurse inserted successfully!");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Insert failed.");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    //READ OPERATIONS

    public void displayAllMedicalStaff() {
        String sql = "SELECT * FROM medical_staff ORDER BY staff_id";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("\n========================================");
            System.out.println("   ALL MEDICAL STAFF FROM DATABASE");
            System.out.println("========================================");

            int count = 0;
            while (resultSet.next()) {
                count++;
                int id = resultSet.getInt("staff_id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                int experience = resultSet.getInt("experience_years");
                String staffType = resultSet.getString("staff_type");

                System.out.println(count + ". [" + staffType + "] " + name);
                System.out.println("   ID: " + id);
                System.out.println("   Department: " + department);
                System.out.println("   Experience: " + experience + " years");

                // Display type-specific information
                if (staffType.equals("DOCTOR")) {
                    String specialization = resultSet.getString("specialization");
                    System.out.println("   Specialization: " + specialization);

                    // Check if specialist (experience >= 5)
                    if (experience >= 5) {
                        System.out.println("   Specialist");
                    }
                } else if (staffType.equals("NURSE")) {
                    int patientsAssigned = resultSet.getInt("patients_assigned");
                    System.out.println("   Patients Assigned: " + patientsAssigned);

                    // Check if head nurse (experience >= 10)
                    if (experience >= 10) {
                        System.out.println("   Head Nurse");
                    }
                }
                System.out.println();
            }

            if (count == 0) {
                System.out.println("No medical staff found in database.");
            } else {
                System.out.println("Total staff members: " + count);
            }
            System.out.println("========================================");

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public MedicalStaff getStaffById(int staffId) {
        String sql = "SELECT * FROM medical_staff WHERE staff_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, staffId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                int experience = resultSet.getInt("experience_years");
                String staffType = resultSet.getString("staff_type");

                MedicalStaff staff = null;

                if (staffType.equals("DOCTOR")) {
                    String specialization = resultSet.getString("specialization");
                    staff = new Doctor(id, name, department, experience, specialization);
                } else if (staffType.equals("NURSE")) {
                    int patientsAssigned = resultSet.getInt("patients_assigned");
                    staff = new Nurse(id, name, department, experience, patientsAssigned);
                }

                resultSet.close();
                statement.close();
                return staff;
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

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM medical_staff WHERE staff_type = 'DOCTOR' ORDER BY name";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                int experience = resultSet.getInt("experience_years");
                String specialization = resultSet.getString("specialization");

                doctors.add(new Doctor(id, name, department, experience, specialization));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("❌ Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return doctors;
    }

    public List<Nurse> getAllNurses() {
        List<Nurse> nurses = new ArrayList<>();
        String sql = "SELECT * FROM medical_staff WHERE staff_type = 'NURSE' ORDER BY name";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                int experience = resultSet.getInt("experience_years");
                int patientsAssigned = resultSet.getInt("patients_assigned");

                nurses.add(new Nurse(id, name, department, experience, patientsAssigned));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return nurses;
    }

    //UPDATE OPERATIONS

    public boolean updateDoctor(Doctor doctor) {
        String sql = "UPDATE medical_staff SET name = ?, department = ?, " +
                "experience_years = ?, specialization = ? " +
                "WHERE staff_id = ? AND staff_type = 'DOCTOR'";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getDepartment());
            statement.setInt(3, doctor.getExperienceYears());
            statement.setString(4, doctor.getSpecialization());
            statement.setInt(5, doctor.getStaffId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("Doctor updated: " + doctor.getName());
                return true;
            } else {
                System.out.println("No doctor found with ID: " + doctor.getStaffId());
            }
        } catch (SQLException e) {
            System.out.println("Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public boolean updateNurse(Nurse nurse) {
        String sql = "UPDATE medical_staff SET name = ?, department = ?, " +
                "experience_years = ?, patients_assigned = ? " +
                "WHERE staff_id = ? AND staff_type = 'NURSE'";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nurse.getName());
            statement.setString(2, nurse.getDepartment());
            statement.setInt(3, nurse.getExperienceYears());
            statement.setInt(4, nurse.getPatientsAssigned());
            statement.setInt(5, nurse.getStaffId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("Nurse updated: " + nurse.getName());
                return true;
            } else {
                System.out.println("No nurse found with ID: " + nurse.getStaffId());
            }
        } catch (SQLException e) {
            System.out.println("Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    // DELETE OPERATIONS

    public boolean deleteStaff(int staffId) {
        String sql = "DELETE FROM medical_staff WHERE staff_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, staffId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println("Staff deleted (ID: " + staffId + ")");
                return true;
            } else {
                System.out.println("No staff found with ID: " + staffId);
            }
        } catch (SQLException e) {
            System.out.println("Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    //SEARCH OPERATIONS

    public List<MedicalStaff> searchByName(String name) {
        List<MedicalStaff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM medical_staff WHERE name ILIKE ? ORDER BY name";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return staffList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                String staffName = resultSet.getString("name");
                String department = resultSet.getString("department");
                int experience = resultSet.getInt("experience_years");
                String staffType = resultSet.getString("staff_type");

                MedicalStaff staff = null;

                if (staffType.equals("DOCTOR")) {
                    String specialization = resultSet.getString("specialization");
                    staff = new Doctor(id, staffName, department, experience, specialization);
                } else if (staffType.equals("NURSE")) {
                    int patientsAssigned = resultSet.getInt("patients_assigned");
                    staff = new Nurse(id, staffName, department, experience, patientsAssigned);
                }

                if (staff != null) {
                    staffList.add(staff);
                }
            }

            resultSet.close();
            statement.close();
            System.out.println("Found " + staffList.size() + " staff member(s)");
        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return staffList;
    }

    public List<MedicalStaff> searchByDepartment(String department) {
        List<MedicalStaff> staffList = new ArrayList<>();

        String sql = "SELECT * FROM medical_staff WHERE department ILIKE ? ORDER BY name";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return staffList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            // Add wildcards for partial matching
            statement.setString(1, "%" + department + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("staff_id");
                String staffName = resultSet.getString("name");
                String dept = resultSet.getString("department");
                int experience = resultSet.getInt("experience_years");
                String staffType = resultSet.getString("staff_type");

                MedicalStaff staff = null;

                if (staffType.equals("DOCTOR")) {
                    String specialization = resultSet.getString("specialization");
                    staff = new Doctor(id, staffName, dept, experience, specialization);
                } else if (staffType.equals("NURSE")) {
                    int patientsAssigned = resultSet.getInt("patients_assigned");
                    staff = new Nurse(id, staffName, dept, experience, patientsAssigned);
                }

                if (staff != null) {
                    staffList.add(staff);
                }
            }

            resultSet.close();
            statement.close();
            System.out.println("Found " + staffList.size() + " staff member(s) in department: " + department);
        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return staffList;
    }
}