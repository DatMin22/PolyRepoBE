package com.PolyRepo.PolyRepo.payload.request;

import java.util.List;

public class ShareRequest {
    private int post_id;
    private int user_id;
    private int user_id_getshare;
    private String ShareStatus;
    private String content;
    private String link;

    public ShareRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public int getUser_id_getshare() {
        return user_id_getshare;
    }

    public void setUser_id_getshare(int user_id_getshare) {
        this.user_id_getshare = user_id_getshare;
    }
}
