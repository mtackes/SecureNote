package edu.mtackes.securenote.model.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mtackes on 12/8/15.
 */
public class User {
    private int id;
    private String userName;
    private Set<CryptoNote> notes = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<CryptoNote> getNotes() {
        return notes;
    }

    public void setNotes(Set<CryptoNote> notes) {
        this.notes = notes;
    }
}
