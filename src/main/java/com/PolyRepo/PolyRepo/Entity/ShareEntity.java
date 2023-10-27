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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUsers() {
        return user;
    }

    public void setUsers(UserEntity users) {
        this.user = users;
    }
}
