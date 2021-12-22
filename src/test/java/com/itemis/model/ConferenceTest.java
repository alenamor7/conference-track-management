package com.itemis.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class ConferenceTest {

    @Test
    public void toString_twoTracks_pass() {
        List<Talk> talks = new LinkedList<>();
        talks.add(new Talk("Writing Fast Tests Against Enterprise Rails", 120));
        talks.add(new Talk("Overdoing it in Python", 90));
        talks.add(new Talk("Lua for the Masses", 45));
        talks.add(new Talk("Common Ruby", 180));
        talks.add(new Talk("Rails for Python", 60));
        talks.add(new Talk("Accounting-Driven Development", 120));
        talks.add(new Talk("Pair Programming vs Noise", 90));
        talks.add(new Talk("Rails Magic", 110));

        Conference conference = new Conference(talks);
        Assertions.assertEquals(2, conference.getTracks().size());
        String expectedResult = String.join("\n",
                "1 Track",
                "09:00AM Writing Fast Tests Against Enterprise Rails 120min",
                "11:00AM Lua for the Masses 45min",
                "12:00PM Lunch",
                "01:00PM Overdoing it in Python 90min",
                "02:30PM Rails for Python 60min",
                "03:30PM Pair Programming vs Noise 90min",
                "05:00PM Networking Event\n",
                "2 Track",
                "09:00AM Common Ruby 180min",
                "12:00PM Lunch",
                "01:00PM Accounting-Driven Development 120min",
                "03:00PM Rails Magic 110min",
                "04:50PM Networking Event");
        Assertions.assertEquals(expectedResult, conference.toString());
    }
}
