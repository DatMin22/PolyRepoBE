package com.PolyRepo.PolyRepo.Entity;

import jakarta.persistence.*;

@Entity(name="comment")

public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="content")
    private String content;

    @Column(name="commentstatus")
    private String commentstatus;

    @ManyToOne()
    @JoinColumn(name = "post_id")
    PostEntity post;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    UserEntity user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentstatus() {
        return commentstatus;
    }

    public void setCommentstatus(String commentstatus) {
        this.commentstatus = commentstatus;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CommentEntity() {
    }

    public CommentEntity(int id, String content, String commentstatus, PostEntity post, UserEntity user) {
        this.id = id;
        this.content = content;
        this.commentstatus = commentstatus;
        this.post = post;
        this.user = user;
    }
}
