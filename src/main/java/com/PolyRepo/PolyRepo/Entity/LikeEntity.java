package com.PolyRepo.PolyRepo.Entity;

import jakarta.persistence.*;

@Entity(name = "likes")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="likestatus")
    private String likestatus;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    UserEntity user;
    @ManyToOne()
    @JoinColumn(name = "post_id")
    PostEntity posts;



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

    public String getLikeStatus() {
        return likestatus;
    }

    public void setLikeStatus(String likeStatus) {
        likestatus = likeStatus;
    }
}
