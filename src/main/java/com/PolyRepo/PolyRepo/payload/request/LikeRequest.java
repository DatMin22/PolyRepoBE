package com.PolyRepo.PolyRepo.payload.request;

public class LikeRequest {
    private int Post_id;
    private int User_id;
    private String LikeStatus;

    public int getPost_id() {
        return Post_id;
    }

    public void setPost_id(int post_id) {
        Post_id = post_id;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

    public String getLikeStatus() {
        return LikeStatus;
    }

    public void setLikeStatus(String likeStatus) {
        LikeStatus = likeStatus;
    }
}
