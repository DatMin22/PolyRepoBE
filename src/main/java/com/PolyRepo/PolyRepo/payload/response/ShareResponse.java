package com.PolyRepo.PolyRepo.payload.response;

public class ShareResponse {
    private int posts;


    private int user;
    private boolean ShareStatus;

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

    public boolean isShareStatus() {
        return ShareStatus;
    }

    public void setShareStatus(boolean shareStatus) {
        ShareStatus = shareStatus;
    }
}
