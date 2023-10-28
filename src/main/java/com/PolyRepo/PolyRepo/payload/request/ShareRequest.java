package com.PolyRepo.PolyRepo.payload.request;

public class ShareRequest {

    private int posts;


    private int user;
    private Boolean ShareStatus;

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public Boolean getShareStatus() {
        return ShareStatus;
    }

    public void setShareStatus(Boolean shareStatus) {
        ShareStatus = shareStatus;
    }
}
