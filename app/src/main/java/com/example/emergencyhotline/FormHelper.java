package com.example.emergencyhotline;

public class FormHelper {
    String Name;
    String e_fName;
    String lName;
    String e_lName;
    String email;
    String contact;
    String relationship;
    String sex;
    String weight;
    String height;
    String address;
    String status;
    String e_contact;
    String dob;
    String nok;
    String medication;

    public FormHelper() {

    }

    public FormHelper(String name, String e_fName, String lName, String e_lName, String email, String contact, String relationship, String sex, String weight, String height, String address, String status, String e_contact, String dob, String nok, String medication) {
        Name = name;
        this.e_fName = e_fName;
        this.lName = lName;
        this.e_lName = e_lName;
        this.email = email;
        this.contact = contact;
        this.relationship = relationship;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.address = address;
        this.status = status;
        this.e_contact = e_contact;
        this.dob = dob;
        this.nok = nok;
        this.medication = medication;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getE_fName() {
        return e_fName;
    }

    public void setE_fName(String e_fName) {
        this.e_fName = e_fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getE_lName() {
        return e_lName;
    }

    public void setE_lName(String e_lName) {
        this.e_lName = e_lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getE_contact() {
        return e_contact;
    }

    public void setE_contact(String e_contact) {
        this.e_contact = e_contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNok() {
        return nok;
    }

    public void setNok(String nok) {
        this.nok = nok;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }
}