package com.PolyRepo.PolyRepo.payload.response;

public class ShareResponse {
    private int id;
    private int post_id;
    private int user_id;
    private Boolean ShareStatus;

    public ShareResponse() {
    }

    public ShareResponse(int id, int post_id, int user_id, Boolean shareStatus) {
        this.id = id;
        this.post_id = post_id;
        this.user_id = user_id;
        ShareStatus = shareStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getShareStatus() {
        return ShareStatus;
    }

    public void setShareStatus(Boolean shareStatus) {
        ShareStatus = shareStatus;
    }
}
