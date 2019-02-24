/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;
import java.time.*;
/**
 *
 * @author SONY
 */
public class Timing {
    private LocalTime bvisitinghour;
    private LocalTime evisitinghour;
    private boolean[] visitingdays = new boolean[7];

    /**
     * @return the bvisitinghour
     */
    public LocalTime getBvisitinghour() {
        return bvisitinghour;
    }

    /**
     * @param bvisitinghour the bvisitinghour to set
     */
    public void setBvisitinghour(LocalTime bvisitinghour) {
        this.bvisitinghour = bvisitinghour;
    }

    /**
     * @return the evisitinghour
     */
    public LocalTime getEvisitinghour() {
        return evisitinghour;
    }

    /**
     * @param evisitinghour the evisitinghour to set
     */
    public void setEvisitinghour(LocalTime evisitinghour) {
        this.evisitinghour = evisitinghour;
    }

    /**
     * @return the visitingdays
     */
    public boolean[] getVisitingdays() {
        return visitingdays;
    }

    /**
     * @param visitingdays the visitingdays to set
     */
    public void setVisitingdays(boolean[] visitingdays) {
        this.visitingdays = visitingdays;
    }
}
