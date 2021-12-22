package com.itemis.model;

import com.itemis.constant.ConferenceConstant;

import java.time.LocalTime;

/**
 * The Task class represents the POJO of a conference talk.
 * The class contains 3 fields: talk's title and duration, which are initialized within a constructor; and
 * startTime which is set while Talks allocation and helps for Talk printing(we don't need to recalculate start time
 * again after allocation and while printing)
 */
public class Talk {

    private String title;
    private int duration;
    private LocalTime startTime;

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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return startTime.format(ConferenceConstant.DATE_FORMATTER) + " "
                + title + " "
                + duration + "min";
    }
}
