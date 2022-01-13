package com.itemis.model;

import com.itemis.constant.ConferenceConstant;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Session class can be instantiated as morning or afternoon session.
 * <p>The class contains<
 *  <ul>
 *      <li>list of talks</li>
 *      <li>duration of a session because morning and afternoon sessions have different durations</li>
 *      <li>remainingDuration helps to detect if Talk can be added into Session</li>
 *      <li>lastTaskEndDate is necessary for setting start date to a Talk when it's added to Session</li>
 *  </ul>
 * /p>
 */
public class Session {

    private List<Talk> talks;
    private int duration;
    private int remainingDuration;
    private LocalTime lastTaskEndDate;


    /**
     * Sessions won't be created by a constructor, they're created by static methods
     */
    private Session() {
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRemainingDuration() {
        return remainingDuration;
    }

    public void setRemainingDuration(int remainingDuration) {
        this.remainingDuration = remainingDuration;
    }

    public LocalTime getLastTaskEndDate() {
        return lastTaskEndDate;
    }

    public void setLastTaskEndDate(LocalTime lastTaskEndDate) {
        this.lastTaskEndDate = lastTaskEndDate;
    }

    /**
     * This static method creates an object of morning Session instead of a constructor.
     * Duration and remainingDuration are equal because talks list is empty. lastTaskEndDate is equal to
     * the time when morning session begins.
     *
     * @return an object of morning Session
     */
    public static Session createMorningSession() {
        Session session = new Session();
        List<Talk> talks = new LinkedList<>();
        session.talks = talks;
        session.duration = ConferenceConstant.MORNING_SESSION_MAX_DURATION;
        session.remainingDuration = ConferenceConstant.MORNING_SESSION_MAX_DURATION;
        session.lastTaskEndDate = ConferenceConstant.MORNING_SESSION_START_TIME;
        return session;
    }

    /**
     * This static method creates an object of afternoon Session instead of a constructor.
     * Duration and remainingDuration are equal because talks list is empty. lastTaskEndDate is equal to
     * the time when afternoon session begins.
     *
     * @return an object of afternoon Session
     */
    public static Session createAfternoonSession() {
        Session session = new Session();
        List<Talk> talks = new LinkedList<>();
        session.talks = talks;
        session.duration = ConferenceConstant.AFTERNOON_SESSION_MAX_DURATION;
        session.remainingDuration = ConferenceConstant.AFTERNOON_SESSION_MAX_DURATION;
        session.lastTaskEndDate = ConferenceConstant.AFTERNOON_SESSION_START_TIME;
        return session;
    }

    /**
     * This method checks if Talk can be added to this session based on its duration. If Talk can be added
     * we set startTime to this Talk, add it to the Session Talks list and change both remainingDuration
     * and lastTaskEndDate.
     *
     * @param talk to be added
     * @return true if Talk with its duration was added
     */
    public boolean addTalk(Talk talk) {
        int talkDuration = talk.getDuration();
        if (remainingDuration - talkDuration >= 0) {
            talk.setStartTime(getLastTaskEndDate());
            talks.add(talk);
            lastTaskEndDate = lastTaskEndDate.plusMinutes(talkDuration);
            remainingDuration -= talkDuration;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (duration != session.duration) return false;
        if (remainingDuration != session.remainingDuration) return false;
        if (talks != null ? !talks.equals(session.talks) : session.talks != null) return false;
        return lastTaskEndDate != null ? lastTaskEndDate.equals(session.lastTaskEndDate) : session.lastTaskEndDate == null;
    }

    @Override
    public int hashCode() {
        int result = talks != null ? talks.hashCode() : 0;
        result = 31 * result + duration;
        result = 31 * result + remainingDuration;
        result = 31 * result + (lastTaskEndDate != null ? lastTaskEndDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        for (Talk talk : talks) {
            stringJoiner.add(talk.toString());
        }
        return stringJoiner.toString();
    }
}
