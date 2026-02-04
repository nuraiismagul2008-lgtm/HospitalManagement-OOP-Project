package com.hospital.management.menu;

import com.hospital.management.database.*;
import com.hospital.management.model.*;
import java.util.List;
import java.util.Scanner;

public class MenuManagment implements Menu {
    private final Scanner scanner;
    private final PatientDAO patientDAO;
    private final AppointmentDAO appointmentDAO;
    private final MedicalStaffDAO staffDAO;

    public MenuManagment() {
        this.scanner = new Scanner(System.in);
        this.patientDAO = new PatientDAO();
        this.appointmentDAO = new AppointmentDAO();
        this.staffDAO = new MedicalStaffDAO();
    }

    @Override
    public void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("     HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("PATIENT MANAGEMENT:");
        System.out.println("1. Add Patient");
        System.out.println("2. View All Patients");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");
        System.out.println();
        System.out.println("MEDICAL STAFF MANAGEMENT:");
        System.out.println("5. Add Doctor");
        System.out.println("6. Add Nurse");
        System.out.println("7. View All Medical Staff");
        System.out.println("8. View Doctors Only");
        System.out.println("9. View Nurses Only");
        System.out.println("10. Update Medical Staff");
        System.out.println("11. Delete Medical Staff");
        System.out.println();
        System.out.println("APPOINTMENT MANAGEMENT:");
        System.out.println("12. Add Appointment");
        System.out.println("13. View All Appointments");
        System.out.println("14. Update Appointment");
        System.out.println("15. Cancel Appointment");
        System.out.println("16. Delete Appointment");
        System.out.println();
        System.out.println("SEARCH & FILTER:");
        System.out.println("17. Search Patient by Name");
        System.out.println("18. Search Patient by Blood Type");
        System.out.println("19. Search Staff by Name");
        System.out.println("20. Search Staff by Department");
        System.out.println();
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            try {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addPatient();
                    case 2 -> viewAllPatients();
                    case 3 -> updatePatient();
                    case 4 -> deletePatient();
                    case 5 -> addDoctor();
                    case 6 -> addNurse();
                    case 7 -> viewAllMedicalStaff();
                    case 8 -> viewDoctorsOnly();
                    case 9 -> viewNursesOnly();
                    case 10 -> updateMedicalStaff();
                    case 11 -> deleteMedicalStaff();
                    case 12 -> addAppointment();
                    case 13 -> viewAllAppointments();
                    case 14 -> updateAppointment();
                    case 15 -> cancelAppointment();
                    case 16 -> deleteAppointment();
                    case 17 -> searchPatientByName();
                    case 18 -> searchPatientByBloodType();
                    case 19 -> searchStaffByName();
                    case 20 -> searchStaffByDepartment();
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


    //PATIENT OPERATIONS
    private void addPatient() {
        try {
            System.out.println("\n--- ADD PATIENT ---");
            System.out.print("Enter full name: ");
            String name = scanner.nextLine();

            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter blood type (A+, A-, B+, B-, AB+, AB-, O+, O-): ");
            String bloodType = scanner.nextLine();

            Patient patient = new Patient(0, name, age, bloodType);
            patientDAO.insertPatient(patient);
        } catch (Exception e) {
            System.out.println("Error adding patient: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void viewAllPatients() {
        patientDAO.displayAllPatients();
    }

    private void updatePatient() {
        try {
            System.out.print("Enter Patient ID to update: ");
            int patientId = scanner.nextInt();
            scanner.nextLine();

            // Load current patient
            Patient existing = patientDAO.getPatientById(patientId);
            if (existing == null) {
                System.out.println("No patient found with ID: " + patientId);
                return;
            }

            // Display current info
            System.out.println("\nCurrent Info:");
            System.out.println("Name: " + existing.getFullName());
            System.out.println("Age: " + existing.getAge());
            System.out.println("Blood Type: " + existing.getBloodType());

            // Get new values
            System.out.print("\nNew Name [" + existing.getFullName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                newName = existing.getFullName();
            }

            System.out.print("New Age [" + existing.getAge() + "]: ");
            String ageInput = scanner.nextLine();
            int newAge = ageInput.trim().isEmpty() ? existing.getAge() : Integer.parseInt(ageInput);

            System.out.print("New Blood Type [" + existing.getBloodType() + "]: ");
            String newBloodType = scanner.nextLine();
            if (newBloodType.trim().isEmpty()) {
                newBloodType = existing.getBloodType();
            }

            // Update
            Patient updated = new Patient(patientId, newName, newAge, newBloodType);
            patientDAO.updatePatient(updated);
        } catch (Exception e) {
            System.out.println("Error updating patient: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void deletePatient() {
        try {
            System.out.print("Enter Patient ID to delete: ");
            int patientId = scanner.nextInt();
            scanner.nextLine();

            // Load and show patient
            Patient patient = patientDAO.getPatientById(patientId);
            if (patient == null) {
                System.out.println("No patient found with ID: " + patientId);
                return;
            }

            // Display patient details
            System.out.println("\nPatient to delete:");
            System.out.println("ID: " + patient.getPatientId());
            System.out.println("Name: " + patient.getFullName());
            System.out.println("Age: " + patient.getAge());
            System.out.println("Blood Type: " + patient.getBloodType());

            // Confirmation
            System.out.print("\nAre you sure? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                patientDAO.deletePatient(patientId);
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting patient: " + e.getMessage());
            scanner.nextLine();
        }
    }

    //MEDICAL STAFF OPERATIONS
    private void addDoctor() {
        try {
            System.out.println("\n--- ADD DOCTOR ---");
            System.out.print("Enter doctor name: ");
            String name = scanner.nextLine();

            System.out.print("Enter department: ");
            String department = scanner.nextLine();

            System.out.print("Enter years of experience: ");
            int experience = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter specialization: ");
            String specialization = scanner.nextLine();

            Doctor doctor = new Doctor(0, name, department, experience, specialization);
            staffDAO.insertDoctor(doctor);
        } catch (Exception e) {
            System.out.println("Error adding doctor: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void addNurse() {
        try {
            System.out.println("\n--- ADD NURSE ---");
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Enter department: ");
            String department = scanner.nextLine();

            System.out.print("Enter years of experience: ");
            int experience = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter number of patients assigned: ");
            int patientsAssigned = scanner.nextInt();
            scanner.nextLine();

            Nurse nurse = new Nurse(0, name, department, experience, patientsAssigned);
            staffDAO.insertNurse(nurse);
        } catch (Exception e) {
            System.out.println("Error adding nurse: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void viewAllMedicalStaff() {
        staffDAO.displayAllMedicalStaff();
    }

    private void viewDoctorsOnly() {
        List<Doctor> doctors = staffDAO.getAllDoctors();

        System.out.println("          DOCTORS ONLY");

        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
            return;
        }

        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            System.out.println((i + 1) + ". " + doctor.getName());
            System.out.println("   ID: " + doctor.getStaffId());
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

        System.out.println("Total doctors: " + doctors.size());
    }

    private void viewNursesOnly() {
        List<Nurse> nurses = staffDAO.getAllNurses();

        System.out.println("          NURSES ONLY");

        if (nurses.isEmpty()) {
            System.out.println("No nurses found.");
            return;
        }

        for (int i = 0; i < nurses.size(); i++) {
            Nurse nurse = nurses.get(i);
            System.out.println((i + 1) + ". " + nurse.getName());
            System.out.println("   ID: " + nurse.getStaffId());
            System.out.println("   Department: " + nurse.getDepartment());
            System.out.println("   Patients Assigned: " + nurse.getPatientsAssigned());
            System.out.println("   Experience: " + nurse.getExperienceYears() + " years");

            if (nurse.isHeadNurse()) {
                System.out.println("   Status: Head Nurse");
            }
            System.out.println();
        }

        System.out.println("Total nurses: " + nurses.size());
    }

    private void updateMedicalStaff() {
        try {
            System.out.print("Enter Staff ID to update: ");
            int staffId = scanner.nextInt();
            scanner.nextLine();

            // Load current staff
            MedicalStaff existing = staffDAO.getStaffById(staffId);
            if (existing == null) {
                System.out.println("No staff found with ID: " + staffId);
                return;
            }

            // Display current info
            System.out.println("\nCurrent Info:");
            System.out.println(existing.toString());

            // Get new values
            System.out.print("\nNew Name [" + existing.getName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                newName = existing.getName();
            }

            System.out.print("New Department [" + existing.getDepartment() + "]: ");
            String newDepartment = scanner.nextLine();
            if (newDepartment.trim().isEmpty()) {
                newDepartment = existing.getDepartment();
            }

            System.out.print("New Experience Years [" + existing.getExperienceYears() + "]: ");
            String expInput = scanner.nextLine();
            int newExperience = expInput.trim().isEmpty() ? existing.getExperienceYears() : Integer.parseInt(expInput);

            // Update based on type
            if (existing instanceof Doctor) {
                Doctor doctor = (Doctor) existing;
                System.out.print("New Specialization [" + doctor.getSpecialization() + "]: ");
                String newSpec = scanner.nextLine();
                if (newSpec.trim().isEmpty()) {
                    newSpec = doctor.getSpecialization();
                }

                Doctor updated = new Doctor(staffId, newName, newDepartment, newExperience, newSpec);
                staffDAO.updateDoctor(updated);
            } else if (existing instanceof Nurse) {
                Nurse nurse = (Nurse) existing;
                System.out.print("New Patients Assigned [" + nurse.getPatientsAssigned() + "]: ");
                String patientsInput = scanner.nextLine();
                int newPatients = patientsInput.trim().isEmpty() ? nurse.getPatientsAssigned() : Integer.parseInt(patientsInput);

                Nurse updated = new Nurse(staffId, newName, newDepartment, newExperience, newPatients);
                staffDAO.updateNurse(updated);
            }
        } catch (Exception e) {
            System.out.println("Error updating staff: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void deleteMedicalStaff() {
        try {
            System.out.print("Enter Staff ID to delete: ");
            int staffId = scanner.nextInt();
            scanner.nextLine();

            // Load and show staff
            MedicalStaff staff = staffDAO.getStaffById(staffId);
            if (staff == null) {
                System.out.println("No staff found with ID: " + staffId);
                return;
            }

            // Display staff details
            System.out.println("\nStaff to delete:");
            System.out.println(staff.toString());

            // Ask for confirmation
            System.out.print("\nAre you sure? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                staffDAO.deleteStaff(staffId);
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting staff: " + e.getMessage());
            scanner.nextLine();
        }
    }

    //APPOINTMENT OPERATIONS
    private void addAppointment() {
        try {
            System.out.println("\n--- ADD APPOINTMENT ---");
            System.out.print("Enter patient name: ");
            String patientName = scanner.nextLine();

            System.out.print("Enter doctor name: ");
            String doctorName = scanner.nextLine();

            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            Appointment appointment = new Appointment(0, patientName, doctorName, date);
            appointmentDAO.insertAppointment(appointment);
        } catch (Exception e) {
            System.out.println("Error adding appointment: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void viewAllAppointments() {
        appointmentDAO.displayAllAppointments();
    }

    private void updateAppointment() {
        try {
            System.out.print("Enter Appointment ID to update: ");
            int appointmentId = scanner.nextInt();
            scanner.nextLine();

            // Load current appointment
            Appointment existing = appointmentDAO.getAppointmentById(appointmentId);
            if (existing == null) {
                System.out.println("No appointment found with ID: " + appointmentId);
                return;
            }

            // Display current info
            System.out.println("\nCurrent Info:");
            System.out.println("Patient: " + existing.getPatientName());
            System.out.println("Doctor: " + existing.getDoctorName());
            System.out.println("Date: " + existing.getDate());

            // Get new values
            System.out.print("\nNew Patient Name [" + existing.getPatientName() + "]: ");
            String newPatient = scanner.nextLine();
            if (newPatient.trim().isEmpty()) {
                newPatient = existing.getPatientName();
            }

            System.out.print("New Doctor Name [" + existing.getDoctorName() + "]: ");
            String newDoctor = scanner.nextLine();
            if (newDoctor.trim().isEmpty()) {
                newDoctor = existing.getDoctorName();
            }

            System.out.print("New Date [" + existing.getDate() + "]: ");
            String newDate = scanner.nextLine();
            if (newDate.trim().isEmpty()) {
                newDate = existing.getDate();
            }

            // Update
            Appointment updated = new Appointment(appointmentId, newPatient, newDoctor, newDate);
            appointmentDAO.updateAppointment(updated);
        } catch (Exception e) {
            System.out.println("Error updating appointment: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void cancelAppointment() {
        try {
            System.out.print("Enter Appointment ID to cancel: ");
            int appointmentId = scanner.nextInt();
            scanner.nextLine();

            // Load and show appointment
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
            if (appointment == null) {
                System.out.println("No appointment found with ID: " + appointmentId);
                return;
            }

            // Display appointment details
            System.out.println("\nAppointment to cancel:");
            System.out.println("ID: " + appointment.getAppointmentId());
            System.out.println("Patient: " + appointment.getPatientName());
            System.out.println("Doctor: " + appointment.getDoctorName());
            System.out.println("Date: " + appointment.getDate());

            // Ask for confirmation
            System.out.print("\nAre you sure? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                appointmentDAO.cancelAppointment(appointmentId);
            } else {
                System.out.println("Cancellation aborted.");
            }
        } catch (Exception e) {
            System.out.println("Error cancelling appointment: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void deleteAppointment() {
        try {
            System.out.print("Enter Appointment ID to delete: ");
            int appointmentId = scanner.nextInt();
            scanner.nextLine();

            // Load and show appointment
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
            if (appointment == null) {
                System.out.println("No appointment found with ID: " + appointmentId);
                return;
            }

            // Display appointment details
            System.out.println("\nAppointment to delete:");
            System.out.println("ID: " + appointment.getAppointmentId());
            System.out.println("Patient: " + appointment.getPatientName());
            System.out.println("Doctor: " + appointment.getDoctorName());
            System.out.println("Date: " + appointment.getDate());

            // Confirmation
            System.out.print("\nAre you sure? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                appointmentDAO.deleteAppointment(appointmentId);
            } else {
                System.out.println("Deletion cancelled.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting appointment: " + e.getMessage());
            scanner.nextLine();
        }
    }


    //SEARCH OPERATIONS
    private void searchPatientByName() {
        try {
            System.out.print("Enter patient name to search: ");
            String name = scanner.nextLine();

            List<Patient> results = patientDAO.searchByName(name);

            System.out.println("\n===== SEARCH RESULTS =====");

            if (results.isEmpty()) {
                System.out.println("No patients found with name: " + name);
            } else {
                for (int i = 0; i < results.size(); i++) {
                    Patient patient = results.get(i);
                    System.out.println((i + 1) + ". " + patient.getFullName());
                    System.out.println("   ID: " + patient.getPatientId());
                    System.out.println("   Age: " + patient.getAge());
                    System.out.println("   Blood Type: " + patient.getBloodType());
                    System.out.println();
                }
                System.out.println("Total found: " + results.size());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void searchPatientByBloodType() {
        try {
            System.out.print("Enter patient blood type to search: ");
            String bloodType = scanner.nextLine();

            List<Patient> results = patientDAO.searchByBloodType(bloodType);

            System.out.println("SEARCH RESULTS");

            if (results.isEmpty()) {
                System.out.println("No patients found with blood type: " + bloodType);
            } else {
                for (int i = 0; i < results.size(); i++) {
                    Patient patient = results.get(i);
                    System.out.println((i + 1) + ". " + patient.getFullName());
                    System.out.println("   ID: " + patient.getPatientId());
                    System.out.println("   Age: " + patient.getAge());
                    System.out.println("   Blood Type: " + patient.getBloodType());
                    System.out.println();
                }
                System.out.println("Total found: " + results.size());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void searchStaffByName() {
        try {
            System.out.print("Enter staff name to search: ");
            String name = scanner.nextLine();

            List<MedicalStaff> results = staffDAO.searchByName(name);

            System.out.println("SEARCH RESULTS");

            if (results.isEmpty()) {
                System.out.println("No medical staffs found with name: " + name);
            } else {
                for (int i = 0; i < results.size(); i++) {
                    MedicalStaff staff = results.get(i);
                    System.out.println((i + 1) + ". " + staff.getName());
                    System.out.println("   ID: " + staff.getStaffId());
                    System.out.println("   Department: " + staff.getDepartment());
                    System.out.println("   Experience: " + staff.getExperienceYears() + " years");
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void searchStaffByDepartment() {
         try {
                System.out.print("Enter staff department to search: ");
                String department = scanner.nextLine();

                List<MedicalStaff> results = staffDAO.searchByDepartment(department);

                System.out.println("SEARCH RESULTS");

                if (results.isEmpty()) {
                    System.out.println("No medical staffs found with department: " + department);
                }  else {
                    for (int i = 0; i < results.size(); i++) {
                        MedicalStaff staff  = results.get(i);
                        System.out.println((i + 1) + ". " + staff.getName());
                        System.out.println("   ID: " + staff.getStaffId());
                        System.out.println("   Department: " + staff.getDepartment());
                        System.out.println("   Experience: " + staff.getExperienceYears() + " years");
                    }
                }
         }  catch (Exception e) {
             throw new RuntimeException(e);
         }
        }
}