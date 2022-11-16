package com.example.college.library;

public class Library {
    String library_id, book_id, initial_stock, current_stock, total_issued, enrollment_no;
    String title,author, student_name;
    String submission_date;
    String response, status;
    String sub_name;
    String issue_date;

    public Library(String book_id, String submission_date, String sub_name,String issue_date) {
        this.book_id = book_id;
        this.submission_date = submission_date;
        this.sub_name = sub_name;
        this.issue_date=issue_date;
    }

    public Library(String book_id, String title, String author, String initial_stock, String current_stock, String total_issued) {
        this.book_id = book_id;
        this.initial_stock = initial_stock;
        this.current_stock = current_stock;
        this.total_issued = total_issued;
        this.title = title;
        this.author = author;
    }

    public Library(String library_id, String book_id, String enrollment_no, String student_name, String submission_date) {
        this.library_id = library_id;
        this.book_id = book_id;
        this.enrollment_no = enrollment_no;
        this.student_name = student_name;
        this.submission_date = submission_date;
    }

    public Library(String response, String status) {
        this.response = response;
        this.status = status;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(String library_id) {
        this.library_id = library_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getInitial_stock() {
        return initial_stock;
    }

    public void setInitial_stock(String initial_stock) {
        this.initial_stock = initial_stock;
    }

    public String getCurrent_stock() {
        return current_stock;
    }

    public void setCurrent_stock(String current_stock) {
        this.current_stock = current_stock;
    }

    public String getTotal_issued() {
        return total_issued;
    }

    public void setTotal_issued(String total_issued) {
        this.total_issued = total_issued;
    }

    public String getEnrollment_no() {
        return enrollment_no;
    }

    public void setEnrollment_no(String enrollment_no) {
        this.enrollment_no = enrollment_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
