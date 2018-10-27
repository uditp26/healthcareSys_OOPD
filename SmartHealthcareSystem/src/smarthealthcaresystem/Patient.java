/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthealthcaresystem;
import java.util.*;
/**
 *
 * @author SONY
 */
public class Patient {
    private String pid;
    private String fname;
    private String lname;
    private boolean location;
    private String address;
    private long phone;
    private boolean condition;
    private char gender;
    private Date dob;
    private String guardian;
    private long emergencyphone;
    private String email;

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the location
     */
    public boolean isLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(boolean location) {
        this.location = location;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public long getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(long phone) {
        this.phone = phone;
    }

    /**
     * @return the condition
     */
    public boolean isCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    /**
     * @return the gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the guardian
     */
    public String getGuardian() {
        return guardian;
    }

    /**
     * @param guardian the guardian to set
     */
    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    /**
     * @return the emergencyphone
     */
    public long getEmergencyphone() {
        return emergencyphone;
    }

    /**
     * @param emergencyphone the emergencyphone to set
     */
    public void setEmergencyphone(long emergencyphone) {
        this.emergencyphone = emergencyphone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
