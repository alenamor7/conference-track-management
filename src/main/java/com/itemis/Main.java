package com.itemis;

import com.itemis.constant.ConferenceConstant;
import com.itemis.exception.IncorrectTalkFormatException;
import com.itemis.model.Talk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        try {
            List<Talk> readTalks = readTalkCollectionFromFile(args[0]);
            // TODO: continue implementing main logic
        } catch (Exception ex) {
            // TODO: add handling
        }
    }

    public static List<Talk> readTalkCollectionFromFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<Talk> inputTalks = new LinkedList<>();

        Stream<String> talkLines = Files.lines(path);
        talkLines.forEach(talkLine -> {
            try {
                inputTalks.add(parseLineToTalk(talkLine));
            } catch (IncorrectTalkFormatException ex) {
                // TODO: add handling
            }
        });
        return inputTalks;
    }

    public static Talk parseLineToTalk(String talkLine) throws IncorrectTalkFormatException {
        Pattern minutePattern = Pattern.compile("\\d+min$");
        Pattern lightningPattern = Pattern.compile("lightning$");

        Matcher minuteMatcher = minutePattern.matcher(talkLine);
        Matcher lightningMatcher = lightningPattern.matcher(talkLine);

        if (minuteMatcher.find()) {
            String matchedLine = minuteMatcher.group();
            int duration = Integer.parseInt(matchedLine.replace("min", ""));
            if (duration > ConferenceConstant.AFTERNOON_SESSION_MAX_DURATION) {
                throw new IncorrectTalkFormatException("Talk cannot exceed the max duration of afternoon session");
            }
            String talkTitle = talkLine.substring(0, minuteMatcher.start()).trim();
            if (talkTitle.length() == 0) {
                throw new IncorrectTalkFormatException("Talk title should not be empty");
            }
            return new Talk(talkTitle, duration);
        } else if (lightningMatcher.find()) {
            int duration = ConferenceConstant.LIGHTNING_DURATION;
            return new Talk(talkLine, duration);
        } else {
            throw new IncorrectTalkFormatException("Incorrect type of talk: it should contain either duration in minutes or lightning");
        }
    }

}
