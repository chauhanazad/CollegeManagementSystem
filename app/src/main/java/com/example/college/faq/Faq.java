package com.example.college.faq;

public class Faq {

    String faq_id;
    String title;
    String question;
    String reply;
    String image_path;
    String name;
    String response;

    public Faq(String faq_id, String title,String question, String reply, String image_path, String name, String response) {
        this.faq_id = faq_id;
        this.title=title;
        this.question = question;
        this.reply = reply;
        this.image_path = image_path;
        this.name = name;
        this.response = response;
    }


    public Faq(String faq_id, String image_path, String name, String question, String reply) {
        this.faq_id = faq_id;
        this.image_path = image_path;
        this.name = name;
        this.question=question;
        this.reply=reply;
    }

    public Faq(String faq_id, String title, String question, String reply) {
        this.faq_id = faq_id;
        this.title = title;
        this.question = question;
        this.reply = reply;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFaq_id() {
        return faq_id;
    }

    public void setFaq_id(String faq_id) {
        this.faq_id = faq_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
