package com.PolyRepo.PolyRepo.payload.response;

public class PostResponse {
    private int id;
    private int userId;
    private String title;
    private String description;


    private int countlike;

    public int getCountlike() {
        return countlike;
    }

    public void setCountlike(int countlike) {
        this.countlike = countlike;
    }

    private int categoryId;
    private String postStatus;

    private String filename;
    private int countlike;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCountlike() {
        return countlike;
    }

    public void setCountlike(int countlike) {
        this.countlike = countlike;
    }
}
