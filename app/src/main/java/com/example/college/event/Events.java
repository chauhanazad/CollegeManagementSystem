package com.example.college.event;

public class Events {
    String event_id;
    String name;
    String startdate;
    String enddate;
    String description;
    String place;
    String file_path;
    String response;

    public Events(String event_id, String name, String file_path) {
        this.event_id = event_id;
        this.name = name;
        this.file_path = file_path;
    }

    public Events(String event_id, String name, String startdate, String enddate, String description, String place, String file_path, String response) {
        this.event_id = event_id;
        this.name = name;
        this.startdate = startdate;
        this.enddate = enddate;
        this.description = description;
        this.place = place;
        this.file_path = file_path;
        this.response = response;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setfile_path(String file_path) {
        this.file_path = file_path;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getName() {
        return name;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public String getfile_path() {
        return file_path;
    }

    public String getResponse() {
        return response;
    }
}
