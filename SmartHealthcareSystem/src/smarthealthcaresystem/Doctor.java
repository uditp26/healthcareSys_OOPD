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
public class Doctor {
    private String did;
    private String fname;
    private String lname;
    private String address;
    private long phone;
    private String email;
    private boolean isseniorsurgeon;
    private String category;
    private Timing visitingtime;

    /**
     * @return the did
     */
    public String getDid() {
        return did;
    }

    /**
     * @param did the did to set
     */
    public void setDid(String did) {
        this.did = did;
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

    /**
     * @return the isseniorsurgeon
     */
    public boolean isIsseniorsurgeon() {
        return isseniorsurgeon;
    }

    /**
     * @param isseniorsurgeon the isseniorsurgeon to set
     */
    public void setIsseniorsurgeon(boolean isseniorsurgeon) {
        this.isseniorsurgeon = isseniorsurgeon;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the visitingtime
     */
    public Timing getVisitingtime() {
        return visitingtime;
    }

    /**
     * @param visitingtime the visitingtime to set
     */
    public void setVisitingtime(Timing visitingtime) {
        this.visitingtime = visitingtime;
    }
}
