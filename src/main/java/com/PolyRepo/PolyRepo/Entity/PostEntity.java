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
    @Column(name="countlike")
    private int countlike;

//    public int getCountlike() {
//        return countlike;
//    }

//    public void setCountlike(int countlike) {
//        this.countlike = countlike;
//    }

    @Column(name="filename")
    private String filename;
//    @Column(name="countlike")
//    private int countlike;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @ManyToOne()
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    CategoryEntity category ;
    @OneToMany(mappedBy ="post")
    Set<CommentEntity> comments;

    @OneToMany(mappedBy ="posts")
    Set<ShareEntity> share;

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @OneToMany(mappedBy ="posts")
    Set<LikeEntity> like;

    public PostEntity() {
    }

    public PostEntity(int id, String title, String descriptions, String poststatus, String filename, int countlike, UserEntity user, CategoryEntity category, Set<CommentEntity> comments, Set<ShareEntity> share, Set<LikeEntity> like) {
        this.id = id;
        this.title = title;
        this.descriptions = descriptions;
        this.poststatus = poststatus;
        this.filename = filename;
        this.countlike = countlike;
        this.user = user;
        this.category = category;
        this.comments = comments;
        this.share = share;
        this.like = like;
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

    public int getCountlike() {
        return countlike;
    }

    public void setCountlike(int countlike) {
        this.countlike = countlike;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    public Set<ShareEntity> getShare() {
        return share;
    }

    public void setShare(Set<ShareEntity> share) {
        this.share = share;
    }

    public Set<LikeEntity> getLike() {
        return like;
    }

    public void setLike(Set<LikeEntity> like) {
        this.like = like;
    }
}
