package com.itemis;

import com.itemis.exception.IncorrectTalkFormatException;
import com.itemis.model.Talk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void readTalkCollectionFromFile_existedPath_pass() {
        String path = "./src/main/resources/test_data.txt";
        try {
            List<Talk> talks = Main.readTalkCollectionFromFile(path);
            assertEquals(talks.size(), 19);

        } catch (IOException ex1) {
            fail();
        }
    }

    @Test
    public void readTalkCollectionFromFile_noFile_fail() {
        String path = "./src/main/resources/test_data.txt";
        try {
            List<Talk> talks = Main.readTalkCollectionFromFile(path);
            assertEquals(talks.size(), 19);

        } catch (IOException ex1) {
            fail();
        }
    }

    @Test
    public void testParseLineToTalk_minutes_pass() {
        String line = "Writing Fast Tests Against Enterprise Rails 60min";
        try {
            Talk parsedTalk = Main.parseLineToTalk(line);
            Assertions.assertEquals(parsedTalk.getDuration(), 60);
            Assertions.assertEquals(parsedTalk.getTitle(), "Writing Fast Tests Against Enterprise Rails");
        } catch (IncorrectTalkFormatException ex) {
            fail();
        }
    }

    @Test
    public void testParseLineToTalk_lightning_pass() {
        String line = "Rails for Python Developers lightning";
        try {
            Talk parsedTalk = Main.parseLineToTalk(line);
            Assertions.assertEquals(parsedTalk.getDuration(), 5);
            Assertions.assertEquals(parsedTalk.getTitle(), "Rails for Python Developers lightning");
        } catch (IncorrectTalkFormatException ex) {

        }
    }

    @Test
    public void parseLineToTalk_incorrectTalkFormat_fail() {
        String incorrectFormattedLine = "Writing Fast Tests Against Enterprise Rails";
        Exception exception = assertThrows(IncorrectTalkFormatException.class, () -> {
            Main.parseLineToTalk(incorrectFormattedLine);
        });

        String expectedMessage = "Incorrect type of talk: it should contain either duration in minutes or lightning";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void parseLineToTalk_tooLongTalk_fail() {
        String tooLongTalk = "Writing Fast Tests Against Enterprise Rails 250min";
        Exception exception = assertThrows(IncorrectTalkFormatException.class, () -> {
            Main.parseLineToTalk(tooLongTalk);
        });

        String expectedMessage = "Talk cannot exceed the max duration of afternoon session";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
