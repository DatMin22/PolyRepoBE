package com.PolyRepo.PolyRepo.Entity;

import jakarta.persistence.*;

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
    private Boolean ShareStatus;

    public ShareEntity() {
    }

    public ShareEntity(int id, UserEntity user, PostEntity posts, Boolean shareStatus) {
        this.id = id;
        this.user = user;
        this.posts = posts;
        ShareStatus = shareStatus;
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

    public Boolean getShareStatus() {
        return ShareStatus;
    }

    public void setShareStatus(Boolean shareStatus) {
        ShareStatus = shareStatus;
    }


}
