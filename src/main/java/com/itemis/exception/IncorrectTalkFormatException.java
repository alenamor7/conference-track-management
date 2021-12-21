package com.itemis.exception;

/**
 * IncorrectTalkFormatException is a checked exception created to detect incorrect input tasks like:
 * empty task title, duration of a talk is less than 10 minutes(no such examples of task in the task description.
 * There are lightnings for short talks purpose)
 */
public class IncorrectTalkFormatException extends Exception {

    public IncorrectTalkFormatException(String message) {
        super(message);
    }
}
