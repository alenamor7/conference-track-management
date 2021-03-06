package com.itemis;

import com.itemis.constant.ConferenceConstant;
import com.itemis.exception.IncorrectTalkFormatException;
import com.itemis.model.Conference;
import com.itemis.model.Talk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Main class represents the entry point of program.
 * Firstly we read file path from arguments args[0], read data from this file and transfer it to the collection of Talks
 * @see com.itemis.model.Talk
 * Moreover, I added logging for errors.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        List<Talk> readTalks = readTalkCollectionFromFile(args[0]);

        /* I decided to leave readTalks collection as it's, because I don't think that it's a good idea to sort
        this collection and therefore put all long-lasting Talks to the first day and leave all short-lasting Talks
         for last days or visa-versa. */
        Conference conference = new Conference(readTalks);

        /* Here's the main printing of results into console. We just call toString method for Conference,
        which calls hierarchically toString methods for Track, Session, Talk. */
        System.out.println(conference);
    }

    /**
     * This method reads data from file placed in filePath and transfers all lines into collection of Talk objects
     *
     * @param filePath represents the path to the file from which Talks need to be read
     * @return the list of Talks
     * @throws IOException
     */
    public static List<Talk> readTalkCollectionFromFile(String filePath) {

        Path path = Paths.get(filePath);
        List<Talk> inputTalks = new LinkedList<>();

        try (Stream<String> talkLines = Files.lines(path)) {
            talkLines.forEach(talkLine -> {
                try {
                    inputTalks.add(parseLineToTalk(talkLine));
                } catch (IncorrectTalkFormatException ex) {
                    logger.error("Incorrect talk format: " + ex);
                }

            });
        } catch (IOException ex) {
            logger.error("Caught exceptions during file reading: " + ex);
        }
        return inputTalks;
    }

    /**
     * This method parses String to a Talk object which can be used further for ordering and sorting into Tracks
     *
     * @param talkLine String as it comes from the file
     * @return Talk object
     * @throws IncorrectTalkFormatException when talk title is empty, talk duration is more them max value
     * or when talk cannot be parsed to either %min or lightning format
     */
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
