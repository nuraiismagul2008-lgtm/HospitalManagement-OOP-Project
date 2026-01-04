package com.hospital.management;

    public class Patient {
        // 1. PRIVATE FIELDS (minimum 4)
        private int patientId;
        private String fullName;
        private int age;
        private String bloodType;

        // 2. CONSTRUCTOR WITH PARAMETERS
        public Patient(int patientId, String fullName, int age, String bloodType) {
            setPatientId(patientId);
            setFullName(fullName);
            setAge(age);
            setBloodType(bloodType);
        }

        // 3. DEFAULT CONSTRUCTOR (optional)
        public Patient() {
            this.patientId = 0;
            this.fullName = "Unknown";
            this.age = 0;
            this.bloodType = "Unknown";
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

        // 5. SETTERS (one for each field) - WITH VALIDATION
        public void setPatientId(int patientId) {
            if (patientId > 0) {
                this.patientId = patientId;
            } else {
                System.out.println("Warning: Patient ID must be positive! Setting to 0.");
                this.patientId = 0;
            }
        }

        public void setFullName(String fullName) {
            if (fullName != null && !fullName.trim().isEmpty()) {
                this.fullName = fullName;
            } else {
                System.out.println("Warning: Name cannot be empty! Keeping current value.");
            }
        }

        public void setAge(int age) {
            if (age >= 0 && age <= 150) {
                this.age = age;
            } else {
                System.out.println("Warning: Age must be between 0 and 150! Setting to 0.");
                this.age = 0;
            }
        }

        public void setBloodType(String bloodType) {
            // Valid blood types: A+, A-, B+, B-, AB+, AB-, O+, O-
            if (bloodType != null &&
                    (bloodType.equals("A+") || bloodType.equals("A-") ||
                            bloodType.equals("B+") || bloodType.equals("B-") ||
                            bloodType.equals("AB+") || bloodType.equals("AB-") ||
                            bloodType.equals("O+") || bloodType.equals("O-"))) {
                this.bloodType = bloodType;
            } else {
                System.out.println("Warning: Invalid blood type! Setting to 'Unknown'.");
                this.bloodType = "Unknown";
            }
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
