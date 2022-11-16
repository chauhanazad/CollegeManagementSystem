package com.example.college.notice;

public class Notice_Details {

    String notice_id;
    String title;
    String message;
    String response;

    public Notice_Details(String notice_id, String title, String message, String response) {
        this.notice_id = notice_id;
        this.title = title;
        this.message = message;
        this.response = response;
    }


    public Notice_Details(String notice_id, String title, String message) {
        this.notice_id = notice_id;
        this.title = title;
        this.message = message;
    }

    public String getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(String notice_id) {
        this.notice_id = notice_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
