package com.PolyRepo.PolyRepo.Entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="passwords")
    private String passwords;

    @Column(name="email")
    private String email;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    RoleEntity role;



    @OneToMany(mappedBy ="user")
    Set<PostEntity> posts;
    @OneToMany(mappedBy ="user")
    Set<CommentEntity> comment;
    public Set<CommentEntity> getComment() {
        return comment;
    }

    public void setComment(Set<CommentEntity> comment) {
        this.comment = comment;
    }

    public Set<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostEntity> posts) {
        this.posts = posts;
    }

    public UserEntity() {
    }

    public UserEntity(int id, String username, String passwords, String email, RoleEntity role) {
        this.id = id;
        this.username = username;
        this.passwords = passwords;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}

