package com.itemis.model;

import java.util.List;

/**
 * Conference class represents the conference and contains the list of tracks.
 * An object of conference can be instantiated by the list of talks which will be formed into sessions and tracks
 * by the constructor
 */
public class Conference {

    private List<Track> tracks;

    /**
     * Conference constructor processes talks and collect them into tracks constructed from sessions
     *
     * @param talks is the list of talks
     */
    public Conference(List<Talk> talks) {
        //TODO: implement  main logic
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
