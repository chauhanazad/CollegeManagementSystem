package com.example.college.assignment;

public class assignment {
    String assignment_id;
    String middlename;
    String sub_name;
    String description;
    String image_path;
    String submission_date;
    String response;
    String sem_name;


    public assignment(String assignment_id, String middlename, String sub_name, String description, String image_path, String submission_date, String response,String sem_name) {
        this.assignment_id = assignment_id;
        this.middlename = middlename;
        this.sub_name = sub_name;
        this.description = description;
        this.image_path = image_path;
        this.submission_date = submission_date;
        this.response = response;
        this.sem_name=sem_name;
    }

    public assignment(String assignment_id, String sub_name, String image_path,String sem_name) {
        this.assignment_id = assignment_id;
        this.sub_name = sub_name;
        this.image_path = image_path;
        this.sem_name=sem_name;
    }

    public String getSem_name() {
        return sem_name;
    }

    public void setSem_name(String sem_name) {
        this.sem_name = sem_name;
    }

    public String getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(String assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getSubmission_date() {
        return submission_date;
    }

    public void setSubmission_date(String submission_date) {
        this.submission_date = submission_date;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
