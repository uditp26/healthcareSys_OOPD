/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;

/**
 *
 * @author SONY
 */
public class PatientHistory {
    private Patient patientdetail;
    private Disease disease;
    private Medicine medicineissued;
    private Test[] tests;

    /**
     * @return the patientdetail
     */
    public Patient getPatientdetail() {
        return patientdetail;
    }

    /**
     * @param patientdetail the patientdetail to set
     */
    public void setPatientdetail(Patient patientdetail) {
        this.patientdetail = patientdetail;
    }

    /**
     * @return the disease
     */
    public Disease getDisease() {
        return disease;
    }

    /**
     * @param disease the disease to set
     */
    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    /**
     * @return the medicineissued
     */
    public Medicine getMedicineissued() {
        return medicineissued;
    }

    /**
     * @param medicineissued the medicineissued to set
     */
    public void setMedicineissued(Medicine medicineissued) {
        this.medicineissued = medicineissued;
    }

    /**
     * @return the tests
     */
    public Test[] getTests() {
        return tests;
    }

    /**
     * @param tests the tests to set
     */
    public void setTests(Test[] tests) {
        this.tests = tests;
    }
}
