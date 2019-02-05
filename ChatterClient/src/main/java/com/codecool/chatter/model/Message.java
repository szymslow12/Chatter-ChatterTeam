package com.codecool.chatter.model;

import java.time.LocalDateTime;

public class Message {

    private long id;
    private User author;
    private String content;
    private LocalDateTime createdAt;

    public Message(User author, String content) {
        this.author = author;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }


    public void setId(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }


    public void setAuthor(User author) {
        this.author = author;
    }


    public User getAuthor() {
        return author;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
