package cleanhood.ny.hack.edu.cleanhood.utilities;

import java.util.ArrayList;

import cleanhood.ny.hack.edu.cleanhood.valueObjects.Event;

/**
 * Created by vaidhyanathannarayanan on 08/04/17.
 */

public class EventList {

    EventList instance = null;
    ArrayList<Event> events;

    private EventList(){
        events = new ArrayList<Event>();
    }

    public EventList getInstance(){
        if(instance == null){
            instance = new EventList();
        }
        return instance;
    }

    

}
