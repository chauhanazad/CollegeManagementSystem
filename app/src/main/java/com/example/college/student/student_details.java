package com.example.college.student;

public class student_details {

    String response;
    String enrollment_no;
    String firstname;
    String middlename;
    String lastname;
    String per_address;
    String per_city;
    String per_pincode;
    String mobile_no;
    String cor_address;
    String cor_city;
    String cor_pincode;
    String cor_mobile_no;
    String state;
    String dob;
    String gender;
    String nationality;
    String caste;
    String subcaste;
    String minority;
    String religion;
    String email;
    String image_path;
    String aadharcard_no;

    String password;
    String board;
    String stream;
    String seat_no;
    String obtained_marks;
    String out_of_marks;
    String percentage;
    String college_joining_date;
    String cource;

    String subId;
    String date;

    String result, total, obtained, sgpa, cgpa;

    public student_details(String response, String enrollment_no, String firstname, String middlename, String lastname, String per_address, String per_city, String per_pincode, String mobile_no, String cor_address, String cor_city, String cor_pincode, String cor_mobile_no, String state, String dob, String gender, String nationality, String caste, String subcaste, String minority, String religion, String email, String image_path, String aadharcard_no, String password, String board, String stream, String seat_no, String obtained_marks, String out_of_marks, String percentage, String college_joining_date, String cource) {
        this.response = response;
        this.enrollment_no = enrollment_no;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.per_address = per_address;
        this.per_city = per_city;
        this.per_pincode = per_pincode;
        this.mobile_no = mobile_no;
        this.cor_address = cor_address;
        this.cor_city = cor_city;
        this.cor_pincode = cor_pincode;
        this.cor_mobile_no = cor_mobile_no;
        this.state = state;
        this.dob = dob;
        this.gender = gender;
        this.nationality = nationality;
        this.caste = caste;
        this.subcaste = subcaste;
        this.minority = minority;
        this.religion = religion;
        this.email = email;
        this.image_path = image_path;
        this.aadharcard_no = aadharcard_no;
        this.password = password;
        this.board = board;
        this.stream = stream;
        this.seat_no = seat_no;
        this.obtained_marks = obtained_marks;
        this.out_of_marks = out_of_marks;
        this.percentage = percentage;
        this.college_joining_date = college_joining_date;
        this.cource = cource;
    }

    public student_details(String enrollment_no, String firstName, String middleName, String lastName, String email, String image_path) {
        this.enrollment_no = enrollment_no;
        this.firstname = firstName;
        this.middlename = middleName;
        this.lastname = lastName;
        this.email = email;
        this.image_path = image_path;
    }



    public void setResponse(String response) {
        this.response = response;
    }

    public void setEnrollment_no(String enrollment_no) {
        this.enrollment_no = enrollment_no;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPer_address(String per_address) {
        this.per_address = per_address;
    }

    public void setPer_city(String per_city) {
        this.per_city = per_city;
    }

    public void setPer_pincode(String per_pincode) {
        this.per_pincode = per_pincode;
    }



    public void setState(String state) {
        this.state = state;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public void setSubcaste(String subcaste) {
        this.subcaste = subcaste;
    }

    public void setMinority(String minority) {
        this.minority = minority;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public void setAadharcard_no(String aadharcard_no) {
        this.aadharcard_no = aadharcard_no;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public void setSeat_no(String seat_no) {
        this.seat_no = seat_no;
    }

    public void setObtained_marks(String obtained_marks) {
        this.obtained_marks = obtained_marks;
    }

    public void setOut_of_marks(String out_of_marks) {
        this.out_of_marks = out_of_marks;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public void setCollege_joining_date(String college_joining_date) {
        this.college_joining_date = college_joining_date;
    }

    public void setCource(String cource) {
        this.cource = cource;
    }

    public String getResponse() {
        return response;
    }

    public String getEnrollment_no() {
        return enrollment_no;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPer_address() {
        return per_address;
    }

    public String getPer_city() {
        return per_city;
    }

    public String getPer_pincode() {
        return per_pincode;
    }


    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getCor_address() {
        return cor_address;
    }

    public void setCor_address(String cor_address) {
        this.cor_address = cor_address;
    }

    public String getCor_city() {
        return cor_city;
    }

    public void setCor_city(String cor_city) {
        this.cor_city = cor_city;
    }

    public String getCor_pincode() {
        return cor_pincode;
    }

    public void setCor_pincode(String cor_pincode) {
        this.cor_pincode = cor_pincode;
    }

    public String getCor_mobile_no() {
        return cor_mobile_no;
    }

    public void setCor_mobile_no(String cor_mobile_no) {
        this.cor_mobile_no = cor_mobile_no;
    }

    public String getState() {
        return state;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getCaste() {
        return caste;
    }

    public String getSubcaste() {
        return subcaste;
    }

    public String getMinority() {
        return minority;
    }

    public String getReligion() {
        return religion;
    }

    public String getEmail() {
        return email;
    }

    public String getImage_path() {
        return image_path;
    }

    public String getAadharcard_no() {
        return aadharcard_no;
    }

    public String getPassword() {
        return password;
    }

    public String getBoard() {
        return board;
    }

    public String getStream() {
        return stream;
    }

    public String getSeat_no() {
        return seat_no;
    }

    public String getObtained_marks() {
        return obtained_marks;
    }

    public String getOut_of_marks() {
        return out_of_marks;
    }

    public String getPercentage() {
        return percentage;
    }

    public String getCollege_joining_date() {
        return college_joining_date;
    }

    public String getCource() {
        return cource;
    }
}
