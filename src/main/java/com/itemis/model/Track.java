package com.itemis.model;

import com.itemis.constant.ConferenceConstant;

import java.time.LocalTime;
import java.util.StringJoiner;

/**
 * Track consists of a morning and an afternoon sessions.
 */
public class Track {

    private static final String lunchTime = LocalTime.of(12, 0).format(ConferenceConstant.DATE_FORMATTER);

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
     * Firstly we check if morning Session doesn't exist - then we create it. Then add Talk to the morning session.
     * If Talk cannot be added to a morning Session, we try to add it to the afternoon Session in the same way:
     * firstly create a new afternoon Session if it doesn't exist and then add Talk to this session.
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
                afternoonSession = Session.createAfternoonSession();
            }
            if (afternoonSession.addTalk(talk)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n");

        //append morning session
        stringJoiner.add(morningSession.toString());

        //append lunch
        stringJoiner.add(lunchTime + " Lunch");

        //append afternoon session and Networking Event(only if afternoon session exists)
        if (afternoonSession != null) {
            stringJoiner.add(afternoonSession.toString());
            Talk lastAfternoonTalk = afternoonSession.getTalks().get(afternoonSession.getTalks().size() - 1);
            LocalTime networkingStartTime = lastAfternoonTalk.getStartTime().plusMinutes(lastAfternoonTalk.getDuration());
            stringJoiner.add(networkingStartTime.format(ConferenceConstant.DATE_FORMATTER) + " Networking Event");
        }

        return stringJoiner.toString();
    }
}
