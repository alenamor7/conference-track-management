package com.itemis.model;

/**
 * The Task class represents the POJO of a conference talk.
 * The class contains 2 fields: talk's title and duration
 */
public class Talk {

    private String title;
    private int duration;

    public Talk(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
