package com.example.BenXe.Model;

public class EmailRequest {
    private String to = "trinhtanphat2403@gmail.com";
    private String subject = "Chao ban";
    private String text = "Toi tên là Phát nè!";

    // Constructor, other methods, and setters here

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
