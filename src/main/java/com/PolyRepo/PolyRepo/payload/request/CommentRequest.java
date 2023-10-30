package com.PolyRepo.PolyRepo.payload.request;

public class CommentRequest {

    private int id;
    private String content;
    private String commentstatus;
    private int post_id;
    private int user_id;

    public CommentRequest() {
    }

    public CommentRequest(int id, String content, String commentstatus, int post_id, int user_id) {
        this.id = id;
        this.content = content;
        this.commentstatus = commentstatus;
        this.post_id = post_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentstatus() {
        return commentstatus;
    }

    public void setCommentstatus(String commentstatus) {
        this.commentstatus = commentstatus;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
