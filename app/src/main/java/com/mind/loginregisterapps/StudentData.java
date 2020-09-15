package com.mind.loginregisterapps;

public class StudentData {

    // Profile

  private   String name,email,address,date,gender;
  private   String eno;
  private   String mno;

    // Skills

  private   String hobbies;
  private   String internship;
  private   String achieve;

    // Qualification

   private String ssc,hsc,diploma,fe,se,te,be;
   private String live,dead;
   private String syear,sbranch;

   // Student Key

    private String key;


    // Default Constuctor

    public StudentData() {
    }


    //  Paramertrized Constructor

    public StudentData(String name, String email, String address, String date, String gender, String eno, String mno, String hobbies, String internship, String achieve, String ssc, String hsc, String diploma, String fe, String se, String te, String be, String live, String dead, String syear, String sbranch) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.date = date;
        this.gender = gender;
        this.eno = eno;
        this.mno = mno;
        this.hobbies = hobbies;
        this.internship = internship;
        this.achieve = achieve;
        this.ssc = ssc;
        this.hsc = hsc;
        this.diploma = diploma;
        this.fe = fe;
        this.se = se;
        this.te = te;
        this.be = be;
        this.live = live;
        this.dead = dead;
        this.syear = syear;
        this.sbranch = sbranch;
    }


    // Member Method


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getGender() {
        return gender;
    }

    public String getEno() {
        return eno;
    }

    public String getMno() {
        return mno;
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getInternship() {
        return internship;
    }

    public String getAchieve() {
        return achieve;
    }

    public String getSsc() {
        return ssc;
    }

    public String getHsc() {
        return hsc;
    }

    public String getDiploma() {
        return diploma;
    }

    public String getFe() {
        return fe;
    }

    public String getSe() {
        return se;
    }

    public String getTe() {
        return te;
    }

    public String getBe() {
        return be;
    }

    public String getLive() {
        return live;
    }

    public String getDead() {
        return dead;
    }

    public String getSyear() {
        return syear;
    }

    public String getSbranch() {
        return sbranch;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
