package com.PolyRepo.PolyRepo.payload.request;

import java.util.List;

public class ShareRequest {
    private int post_id;
    private int user_id;
    private String ShareStatus;
    private String content;

    public ShareRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getShareStatus() {
        return ShareStatus;
    }

    public void setShareStatus(String shareStatus) {
        ShareStatus = shareStatus;
    }


}
