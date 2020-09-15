package com.mind.loginregisterapps;

public class UpdateData {

    private String title,description;
    private String key;


    public UpdateData(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public UpdateData() {
    }

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
