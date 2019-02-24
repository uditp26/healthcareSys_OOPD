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
public class Department {
    private SeniorSpecialist hod;
    private JuniorResident[] jrresidentdetail;
    private SeniorResident[] srresidentdetail;
    private Specialist[] specialistdetail;
    private SeniorSpecialist[] srspecialistdetail;

    /**
     * @return the hod
     */
    public SeniorSpecialist getHod() {
        return hod;
    }

    /**
     * @param hod the hod to set
     */
    public void setHod(SeniorSpecialist hod) {
        this.hod = hod;
    }

    /**
     * @return the jrresidentdetail
     */
    public JuniorResident[] getJrresidentdetail() {
        return jrresidentdetail;
    }

    /**
     * @param jrresidentdetail the jrresidentdetail to set
     */
    public void setJrresidentdetail(JuniorResident[] jrresidentdetail) {
        this.jrresidentdetail = jrresidentdetail;
    }

    /**
     * @return the srresidentdetail
     */
    public SeniorResident[] getSrresidentdetail() {
        return srresidentdetail;
    }

    /**
     * @param srresidentdetail the srresidentdetail to set
     */
    public void setSrresidentdetail(SeniorResident[] srresidentdetail) {
        this.srresidentdetail = srresidentdetail;
    }

    /**
     * @return the specialistdetail
     */
    public Specialist[] getSpecialistdetail() {
        return specialistdetail;
    }

    /**
     * @param specialistdetail the specialistdetail to set
     */
    public void setSpecialistdetail(Specialist[] specialistdetail) {
        this.specialistdetail = specialistdetail;
    }

    /**
     * @return the srspecialistdetail
     */
    public SeniorSpecialist[] getSrspecialistdetail() {
        return srspecialistdetail;
    }

    /**
     * @param srspecialistdetail the srspecialistdetail to set
     */
    public void setSrspecialistdetail(SeniorSpecialist[] srspecialistdetail) {
        this.srspecialistdetail = srspecialistdetail;
    }
}
