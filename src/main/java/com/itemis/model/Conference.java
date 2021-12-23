package com.itemis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Conference class represents the conference and contains the list of tracks.
 * An object of conference can be instantiated by the list of talks which will be formed into tracks by the constructor
 */
public class Conference {

    private List<Track> tracks = new ArrayList<>();

    /**
     * Conference constructor processes Talks and collect them into Tracks constructed from sessions.
     * Talks are added to Tracks according to the following logic:
     * the collection of Tracks has initially only 1 empty Track, we iterate over the collection of talks and check if
     * the first Track has free space to get this Talk. If yes - we iterate to the next Talk. If no - we create new
     * Track and add the Talk to this Track
     *
     * @param unsortedTalks is a linked list of talks
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
        StringJoiner stringJoiner = new StringJoiner("\n\n");

        int trackNumber = 1;
        for (Track track : tracks) {
            stringJoiner.add(trackNumber + " Track" + "\n" + track.toString());
            trackNumber++;
        }

        return stringJoiner.toString();
    }
}
