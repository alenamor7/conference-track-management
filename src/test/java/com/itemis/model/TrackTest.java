package com.itemis.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrackTest {

    @Test
    public void addTalk_addSeveralTalks_pass() {
        Track track = new Track();
        fillOneSession(track);

        Assertions.assertEquals(3, track.getMorningSession().getTalks().size());
        // all 3 talks were added to the morning session, therefore afternoon session doesn't exist
        Assertions.assertNull(track.getAfternoonSession());

        //add one more talk to fill afternoon session
        Talk fourthTalk = new Talk("A World Without HackerNews", 45);
        track.addTalk(fourthTalk);
        Assertions.assertNotNull(track.getAfternoonSession());
        Assertions.assertEquals(1, track.getAfternoonSession().getTalks().size());
    }

    @Test
    public void toString_onlyMorningSession_pass() {
        Track track = new Track();
        fillOneSession(track);

        String expectedResult = String.join("\n",
                "09:00AM Writing Fast Tests Against Enterprise Rails 60min",
                "10:00AM Lua for the Masses 60min",
                "11:00AM Ruby Errors from Mismatched Gem Versions 45min",
                "12:00PM Lunch");
        Assertions.assertEquals(expectedResult, track.toString());
    }

    @Test
    public void toString_bothSessions_pass() {
        Track track = new Track();
        fillOneSession(track);
        fillOneSession(track);

        String expectedResult = String.join("\n",
                "09:00AM Writing Fast Tests Against Enterprise Rails 60min",
                "10:00AM Lua for the Masses 60min",
                "11:00AM Ruby Errors from Mismatched Gem Versions 45min",
                "12:00PM Lunch",
                "01:00PM Writing Fast Tests Against Enterprise Rails 60min",
                "02:00PM Lua for the Masses 60min",
                "03:00PM Ruby Errors from Mismatched Gem Versions 45min",
                "03:45PM Networking Event");
        Assertions.assertEquals(expectedResult, track.toString());

    }

    private void fillOneSession(Track track) {
        Talk firstTalk = new Talk("Writing Fast Tests Against Enterprise Rails", 60);
        Talk secondTalk = new Talk("Lua for the Masses", 60);
        Talk thirdTalk = new Talk("Ruby Errors from Mismatched Gem Versions", 45);

        track.addTalk(firstTalk);
        track.addTalk(secondTalk);
        track.addTalk(thirdTalk);
    }
}
