package com.example.college.onlineexam;

public class Exam_Detail {
    String exam_id;
    String exam_name;
    String staff_id;
    String sub_id;
    String sub_name;
    String date_added;

    public Exam_Detail(String exam_id, String exam_name, String staff_id, String sub_id, String date_added,String sub_name) {
        this.exam_id = exam_id;
        this.exam_name = exam_name;
        this.staff_id = staff_id;
        this.sub_id = sub_id;
        this.date_added = date_added;
        this.sub_name=sub_name;
    }

    public Exam_Detail(String exam_id, String exam_name, String sub_id, String sub_name, String date_added) {
        this.exam_id = exam_id;
        this.exam_name = exam_name;
        this.sub_id = sub_id;
        this.sub_name = sub_name;
        this.date_added = date_added;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }
}

