
package smarthealthcaresystem;
import java.util.*;

public class Patient {
    private String pid;
    private String fname;
    private String lname;
    private String location;
    private String address;
    private String phone;
    private String condition;
    private String gender;
    private Date dob;
    private String guardian;
    private long emergencyphone;
    private String email;
    public Patient(String pid,String fname,String lname,String location,String phone,String condition,String gender)
    {
        this.pid=pid;
        this.fname=fname;
        this.lname=lname;
        this.location=location;
        this.phone=phone;
        this.condition=condition;
        this.gender=gender;
        
    }
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

  
    public String getFname() {
        return fname;
    }

   
    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String isLocation() {
        return location;
    }

    public void setLocation(String location) {
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
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the condition
     */
    public String isCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
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