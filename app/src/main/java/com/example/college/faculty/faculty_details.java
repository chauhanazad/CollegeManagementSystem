package com.example.college.faculty;


public class faculty_details {

    String password;
    String response;
    String staff_id;
    String firstname;
    String middlename;
    String lastname;
    String dob;
    String age;
    String gender;
    String permanent_address;
    String cor_address;
    String city;
    String state;
    String pincode;
    String mobile_no;
    String cor_mobile_no;
    String email;
    String nationality;
    String religion;
    String image_path;
    String type;
    String qualification;
    String experience;
    String designation;


    public faculty_details(String password, String response, String staff_id, String firstname, String middlename, String lastname, String dob, String age, String gender, String permanent_address, String cor_address, String city, String state, String pincode, String mobile_no, String cor_mobile_no, String email, String nationality, String religion, String image_path, String type, String qualification, String experience, String designation) {
        this.password = password;
        this.response = response;
        this.staff_id = staff_id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
        this.permanent_address = permanent_address;
        this.cor_address = cor_address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.mobile_no = mobile_no;
        this.cor_mobile_no = cor_mobile_no;
        this.email = email;
        this.nationality = nationality;
        this.religion = religion;
        this.image_path = image_path;
        this.type = type;
        this.qualification=qualification;
        this.experience=experience;
        this.designation=designation;
    }

    public String getQualification() {
        return qualification;
    }

    public String getExperience() {
        return experience;
    }

    public String getDesignation() {
        return designation;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCor_address() {
        return cor_address;
    }

    public void setCor_address(String cor_address) {
        this.cor_address = cor_address;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getCor_mobile_no() {
        return cor_mobile_no;
    }

    public void setCor_mobile_no(String cor_mobile_no) {
        this.cor_mobile_no = cor_mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
