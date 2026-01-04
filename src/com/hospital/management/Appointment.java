package com.hospital.management;

public class Appointment {
        // 1. PRIVATE FIELDS (4 fields)
        private int appointmentId;
        private String patientName;
        private String doctorName;
        private String date;

        // 2. CONSTRUCTOR WITH PARAMETERS
        public Appointment(int appointmentId, String patientName, String doctorName, String date) {
            setAppointmentId(appointmentId);
            setPatientName(patientName);
            setDoctorName(doctorName);
            setDate(date);
        }

        // 3. DEFAULT CONSTRUCTOR (optional)
        public Appointment() {
            this.appointmentId = 0;
            this.patientName = "Unknown";
            this.doctorName = "Unknown";
            this.date = "Not scheduled";
        }

        // 4. GETTERS (one for each field)
        public int getAppointmentId() {
            return appointmentId;
        }

        public String getPatientName() {
            return patientName;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public String getDate() {
            return date;
        }

    // 5. SETTERS (one for each field) - WITH VALIDATION

    public void setAppointmentId(int appointmentId) {
        if (appointmentId > 0) {
            this.appointmentId = appointmentId;
        } else {
            System.out.println("Warning: Appointment ID must be positive! Setting to 0.");
            this.appointmentId = 0;
        }
    }

    public void setPatientName(String patientName) {
        if (patientName != null && !patientName.trim().isEmpty()) {
            this.patientName = patientName;
        } else {
            System.out.println("Warning: Patient name cannot be empty! Keeping current value.");
        }
    }

    public void setDoctorName(String doctorName) {
        if (doctorName != null && !doctorName.trim().isEmpty()) {
            this.doctorName = doctorName;
        } else {
            System.out.println("Warning: Doctor name cannot be empty! Keeping current value.");
        }
    }

    public void setDate(String date) {
        if (date != null && !date.trim().isEmpty()) {
            this.date = date;
        } else {
            System.out.println("Warning: Date cannot be empty! Keeping current value.");
        }
    }

        // 6. ADDITIONAL METHODS (minimum 2)

        public void reschedule(String newDate) {
            this.date = newDate;
            System.out.println("Appointment " + appointmentId + " rescheduled to " + newDate);
        }

        public void cancel() {
            this.date = "CANCELLED";
            System.out.println("Appointment " + appointmentId + " has been cancelled");
        }

        public boolean isCancelled() {
            return date.equals("CANCELLED");
        }

        // 7. toString() METHOD
        @Override
        public String toString() {
            return "Appointment{" +
                    "appointmentId=" + appointmentId +
                    ", patientName='" + patientName + '\'' +
                    ", doctorName='" + doctorName + '\'' +
                    ", date='" + date + '\'' +
                    '}';
        }
    }

