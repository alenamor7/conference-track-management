package com.itemis.model;

import java.util.List;

/**
 * Session class represents session class which can be instantiated as morning or afternoon session.
 * The class contains the list of talks included into session
 */
public class Session {

    private List<Talk> talks;
    // TODO: probably add some other fields

    public Session(List<Talk> talks) {
        this.talks = talks;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }
}
