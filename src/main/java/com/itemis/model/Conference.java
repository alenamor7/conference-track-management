package com.itemis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Conference class represents the conference and contains the list of tracks.
 * An object of conference can be instantiated by the list of talks which will be formed into tracks by the constructor
 */
public class Conference {

    private List<Track> tracks = new ArrayList<>();

    /**
     * Conference constructor processes talks and collect them into tracks constructed from sessions
     *
     * @param unsortedTalks is a linked list of talks, which can be modified - it means, I delete Talks from it when
     *                      I place it into a Track. Therefore, it should be better a linked list for faster deleting
     */
    public Conference(List<Talk> unsortedTalks) {
        Track currentTrack = new Track();
        tracks.add(currentTrack);

        for (Talk talk : unsortedTalks) {
            for (Track track : tracks) {
                if (track.addTalk(talk)) {
                    break;
                }
            }
            if (talk.getStartTime() == null) {
                Track extraTrack = new Track();
                extraTrack.addTalk(talk);
                tracks.add(extraTrack);
            }
        }
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int trackNumber = 1;
        for (Track track : tracks) {
            stringBuilder.append(trackNumber + " Track\n");
            stringBuilder.append(track.toString());
            stringBuilder.append("\n");
            trackNumber++;
        }
        return stringBuilder.toString();
    }
}
