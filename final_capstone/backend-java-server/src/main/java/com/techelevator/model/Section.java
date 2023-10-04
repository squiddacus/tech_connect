package com.techelevator.model;

public class Section {

    int id;
    String title;

    public Section(int sectionId, String sectionTitle) {
        this.id = sectionId;
        this.title = sectionTitle;
    }
    public Section() { }

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
}
