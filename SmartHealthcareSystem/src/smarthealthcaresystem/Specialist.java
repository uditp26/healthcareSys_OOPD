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
public class Specialist {
    private String specialisation;
    private boolean issurgeon;

    /**
     * @return the specialisation
     */
    public String getSpecialisation() {
        return specialisation;
    }

    /**
     * @param specialisation the specialisation to set
     */
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    /**
     * @return the issurgeon
     */
    public boolean isIssurgeon() {
        return issurgeon;
    }

    /**
     * @param issurgeon the issurgeon to set
     */
    public void setIssurgeon(boolean issurgeon) {
        this.issurgeon = issurgeon;
    }
}
