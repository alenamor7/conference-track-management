package com.itemis.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class TalkTest {

    @Test
    public void toString_talkInMinutes_pass() {
        Talk talk = new Talk("Woah", 30);
        talk.setStartTime(LocalTime.of(9,0));
        String expectedResult = "09:00AM Woah 30min";
        Assert.assertEquals(expectedResult, talk.toString());
    }

    @Test
    public void toString_talkLightning_pass() {
        Talk talk = new Talk("Rails for Python Developers lightning", 5);
        talk.setStartTime(LocalTime.of(9,0));
        String expectedResult = "09:00AM Rails for Python Developers lightning";
        Assert.assertEquals(expectedResult, talk.toString());
    }
}
