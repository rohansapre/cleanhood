package cleanhood.ny.hack.edu.cleanhood.valueObjects;

import java.util.ArrayList;

/**
 * Created by vaidhyanathannarayanan on 08/04/17.
 */

public class User {

    String userID = "";
    String emailID = "";
    String profilePic = "";
    ArrayList<String> interests = new ArrayList<String>();
    String tokenID = "";

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public String getTokenID() {
        return tokenID;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }
}
