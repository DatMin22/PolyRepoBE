package com.PolyRepo.PolyRepo.payload.request;

public class PostRequest {
private String title;
private String description;
private String poststatus;
private int user_id;

private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    private int category_id;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoststatus() {
        return poststatus;
    }

    public void setPoststatus(String poststatus) {
        this.poststatus = poststatus;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategoy_id(int categoy_id) {
        this.category_id = categoy_id;
    }
}
