package com.PolyRepo.PolyRepo.Entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name="posts")

public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="descriptions")
    private String descriptions;



    @Column(name="poststatus")
    private String poststatus;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    UserEntity user;
    @OneToMany(mappedBy ="post")
    Set<CommentEntity> comments;

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }



    public String getPoststatus() {
        return poststatus;
    }

    public void setPoststatus(String poststatus) {
        this.poststatus = poststatus;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PostEntity() {
    }


}
