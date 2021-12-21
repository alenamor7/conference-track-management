package com.itemis.model;

/**
 * Track consists of a morning and an afternoon sessions.
 */
public class Track {

    private Session morningSession;
    private Session afternoonSession;

    public Track(Session morningSession, Session afternoonSession) {
        this.morningSession = morningSession;
        this.afternoonSession = afternoonSession;
    }

    public Session getMorningSession() {
        return morningSession;
    }

    public void setMorningSession(Session morningSession) {
        this.morningSession = morningSession;
    }

    public Session getAfternoonSession() {
        return afternoonSession;
    }

    public void setAfternoonSession(Session afternoonSession) {
        this.afternoonSession = afternoonSession;
    }
}
