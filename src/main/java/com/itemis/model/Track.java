package com.itemis.model;

/**
 * Track consists of a morning and an afternoon sessions.
 */
public class Track {

    private Session morningSession;
    private Session afternoonSession;

    public Track() {
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

    /**
     * This method adds talk to the Track
     * Firstly I check if morning Session doesn't exist - then we create it. Then add Talk to the morning session.
     * If Talk cannot be added to a morning Session, I try to add it to the afternoon Session in the same way:
     * firstly create new afternoon Session if it doesn't exist and then add Talk to this session.
     *
     * @param talk to be added into Track
     * @return true if talk was successfully added to either morning or afternoon session. Otherwise, returns false.
     */
    public boolean addTalk(Talk talk) {
        if (morningSession == null) {
            morningSession = Session.createMorningSession();
        }
        if (morningSession.addTalk(talk)) {
            return true;
        } else {
            if (afternoonSession == null) {
                if (afternoonSession.addTalk(talk)) {
                    return true;
                }
            }
        }
        return false;
    }
}
