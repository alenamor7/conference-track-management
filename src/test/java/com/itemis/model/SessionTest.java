package com.itemis.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class SessionTest {

    @Test
    public void createMorningSession_correctFields_pass() {
        Session morningSession = Session.createMorningSession();
        Assertions.assertTrue(morningSession.getTalks().isEmpty());
        Assertions.assertEquals(180, morningSession.getDuration());
        Assertions.assertEquals(LocalTime.of(9, 0), morningSession.getLastTaskEndDate());
        Assertions.assertEquals(180, morningSession.getRemainingDuration());
    }

    @Test
    public void createAfternoonSession_correctFields_pass() {
        Session afternoonSession = Session.createAfternoonSession();
        Assertions.assertTrue(afternoonSession.getTalks().isEmpty());
        Assertions.assertEquals(240, afternoonSession.getDuration());
        Assertions.assertEquals(LocalTime.of(13, 0), afternoonSession.getLastTaskEndDate());
        Assertions.assertEquals(240, afternoonSession.getRemainingDuration());
    }

    @Test
    public void addTalk_morningSession_pass() {
        Session morningSession = Session.createMorningSession();
        Talk talk = new Talk("Ruby on Rails: Why We Should Move On", 60);
        morningSession.addTalk(talk);
        Assertions.assertEquals(1, morningSession.getTalks().size());
        Assertions.assertEquals(120, morningSession.getRemainingDuration());
        Assertions.assertEquals(LocalTime.of(10, 0), morningSession.getLastTaskEndDate());
    }

    @Test
    public void addTalk_afternoonSession_pass() {
        Session afternoonSession = Session.createAfternoonSession();
        Talk talk = new Talk("Ruby on Rails: Why We Should Move On", 45);
        afternoonSession.addTalk(talk);
        Assertions.assertEquals(1, afternoonSession.getTalks().size());
        Assertions.assertEquals(195, afternoonSession.getRemainingDuration());
        Assertions.assertEquals(LocalTime.of(13, 45), afternoonSession.getLastTaskEndDate());
    }

    @Test
    public void toString_morningSession_pass() {
        Session morningSession = Session.createMorningSession();
        Talk firstTalk = new Talk("Ruby on Rails: Why We Should Move On", 60);
        Talk secondTalk = new Talk("A World Without HackerNews lightning", 5);
        morningSession.addTalk(firstTalk);
        morningSession.addTalk(secondTalk);
        String expectedResult = String.join("\n",
                "09:00AM Ruby on Rails: Why We Should Move On 60min",
                "10:00AM A World Without HackerNews lightning");
        Assertions.assertEquals(expectedResult, morningSession.toString());
    }

    @Test
    public void toString_afternoonSession_pass() {
        Session afternoonSession = Session.createAfternoonSession();
        Talk firstTalk = new Talk("Ruby on Rails: Why We Should Move On", 60);
        Talk secondTalk = new Talk("A World Without HackerNews lightning", 5);
        afternoonSession.addTalk(firstTalk);
        afternoonSession.addTalk(secondTalk);
        String expectedResult = String.join("\n",
                "01:00PM Ruby on Rails: Why We Should Move On 60min",
                "02:00PM A World Without HackerNews lightning");
        Assertions.assertEquals(expectedResult, afternoonSession.toString());
    }
}
