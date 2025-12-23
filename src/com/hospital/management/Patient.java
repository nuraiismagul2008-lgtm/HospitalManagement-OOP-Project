package com.hospital.management;

    public class Patient {
        // 1. PRIVATE FIELDS (minimum 4)
        private int patientId;
        private String fullName;
        private int age;
        private String bloodType;

        // 2. CONSTRUCTOR WITH PARAMETERS
        public Patient() {
            this.patientId = 0;
            this.fullName = "Unknown";
            this.age = 0;
            this.bloodType = "Unknown";
        }

        // 3. DEFAULT CONSTRUCTOR (optional)
            public Patient(int patientId, String fullName, int age, String bloodType) {
                this.patientId = patientId;
                this.fullName = fullName;
                this.age = age;
                this.bloodType = bloodType;
            }

        // 4. GETTERS (one for each field)
        public int getPatientId() {
            return patientId;
        }
        public String getFullName() {
            return fullName;
        }
        public int getAge() {
            return age;
        }
        public String getBloodType() {
            return bloodType;
        }
        // 5. SETTERS (one for each field)
        public void setPatientId(int patientId) {
            this.patientId = patientId;
        }
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public void setBloodType(String bloodType) {
            this.bloodType = bloodType;
        }
        // 6. ADDITIONAL METHODS (minimum 2)
        public boolean isMinor() {
            return age < 18;
        }
        public String getAgeCategory() {
            if (age < 18) {
                return "Child";
            } else if (age >= 18 && age < 65) {
                return "Adult";
            } else {
                return "Senior";
            }
        }
        @Override
        public String toString() {
            return "Patient{" +
                    "patientId=" + patientId +
                    ", fullName='" + fullName + '\'' +
                    ", age=" + age +
                    ", bloodType='" + bloodType + '\'' +
                    '}';
        }
    }
