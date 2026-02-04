//treatable
package com.hospital.management.model;

public interface Treatable {
    void treat(String patientName);
    String getTreatmentType();
}