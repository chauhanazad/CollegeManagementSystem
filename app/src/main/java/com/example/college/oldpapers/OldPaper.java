package com.example.college.oldpapers;

public class OldPaper {

    String opid;
    String sub_id;
    String sub_name;
    String file_path;
    String year;
    String status;
    String response;

    public OldPaper(String opid, String sub_id, String sub_name, String file_path, String year, String status, String response) {
        this.opid=opid;
        this.sub_id = sub_id;
        this.sub_name = sub_name;
        this.file_path = file_path;
        this.year = year;
        this.status = status;
        this.response=response;
    }

    public OldPaper(String opid, String file_path, String year, String status) {
        this.opid=opid;
        this.file_path = file_path;
        this.year = year;
        this.status = status;
    }

    public String getOpid() {
        return opid;
    }

    public void setOpid(String opid) {
        this.opid = opid;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
