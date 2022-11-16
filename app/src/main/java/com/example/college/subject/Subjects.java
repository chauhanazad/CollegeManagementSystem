package com.example.college.subject;

public class Subjects {

    String sub_id;
    String sub_name;
    String sem_name;
    String sem_id;
    String response;

    public Subjects(String sub_id, String sub_name, String sem_id, String sem_name, String response) {
        this.sub_id = sub_id;
        this.sub_name = sub_name;
        this.sem_id = sem_id;
        this.response=response;
        this.sem_name=sem_name;
    }

    public String getSem_name() {
        return sem_name;
    }

    public void setSem_name(String sem_name) {
        this.sem_name = sem_name;
    }

    public String getResponse() {
        return response;
    }

    public String getSub_id() {
        return sub_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public String getSem_id() {
        return sem_id;
    }
}
