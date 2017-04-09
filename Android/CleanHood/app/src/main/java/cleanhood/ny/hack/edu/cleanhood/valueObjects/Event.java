package cleanhood.ny.hack.edu.cleanhood.valueObjects;

import java.util.ArrayList;

/**
 * Created by vaidhyanathannarayanan on 08/04/17.
 */

public class Event {

    String eventName = "";
    String creatorID = "";
    String eventID = "";
    String initialPicURL = "";
    String endPicURL = "";
    String date = "";
    String time = "";
    ArrayList<String> eventTags = new ArrayList<String>();
    String purpose = "";
    int numParticipants = 0;
    boolean closed = false;
    String location = "";

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getInitialPicURL() {
        return initialPicURL;
    }

    public void setInitialPicURL(String initialPicURL) {
        this.initialPicURL = initialPicURL;
    }

    public String getEndPicURL() {
        return endPicURL;
    }

    public void setEndPicURL(String endPicURL) {
        this.endPicURL = endPicURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<String> getEventTags() {
        return eventTags;
    }

    public void setEventTags(ArrayList<String> eventTags) {
        this.eventTags = eventTags;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getNumParticipants() {
        return numParticipants;
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }

    public boolean getClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
