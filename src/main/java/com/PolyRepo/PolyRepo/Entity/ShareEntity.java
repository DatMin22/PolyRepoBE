package com.PolyRepo.PolyRepo.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Share")

public class ShareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "post_id")
    PostEntity posts;
    @Column(name="sharestatus")
    private String ShareStatus;

    @Column(name="content")
    private String content;

    public ShareEntity() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PostEntity getPosts() {
        return posts;
    }

    public void setPosts(PostEntity posts) {
        this.posts = posts;
    }

    public String getShareStatus() {
        return ShareStatus;
    }

    public void setShareStatus(String shareStatus) {
        ShareStatus = shareStatus;
    }


}
