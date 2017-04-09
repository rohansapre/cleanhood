package cleanhood.ny.hack.edu.cleanhood.valueObjects;

import java.util.ArrayList;

/**
 * Created by vaidhyanathannarayanan on 08/04/17.
 */

public class Event {

    String name = "";
    String _user = "";
    String _id = "";
    String initialPicURL = "";
    String endPicURL = "";
    String date = "";
    String dateCreated = "";
    String time = "";
    String purpose = "";
    int numParticipants = 0;
    boolean closed = false;
    String location = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_User() {
        return _user;
    }

    public void set_User(String user) {
        this._user = user;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
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
