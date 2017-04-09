package cleanhood.ny.hack.edu.cleanhood.utilities;

import java.util.ArrayList;

import cleanhood.ny.hack.edu.cleanhood.valueObjects.Event;

/**
 * Created by vaidhyanathannarayanan on 08/04/17.
 */

public class EventList {

    static EventList instance = null;
    ArrayList<Event> events;

    private EventList(){
        events = new ArrayList<Event>();
    }

    public static EventList getInstance(){
        if(instance == null){
            instance = new EventList();
        }
        return instance;
    }

    public void setEvents(ArrayList<Event> updatedEvents){
        events = updatedEvents;
    }

    public ArrayList<Event> getEvents(){
        return events;
    }

    public ArrayList<Event> getUpcomingEvents(){
        ArrayList<Event> upcomingEvents = new ArrayList<Event>();
        for(Event e : events){
            if(!e.getClosed()){
                upcomingEvents.add(e);
            }
        }
        return upcomingEvents;
    }

    public ArrayList<Event> getPastEvents(){
        ArrayList<Event> pastEvents = new ArrayList<Event>();
        for(Event e : events){
            if(e.getClosed()){
                pastEvents.add(e);
            }
        }
        return pastEvents;
    }

    public ArrayList<Event> getMyHostedEvents(String creatorID){
        ArrayList<Event> myHostedEvents = new ArrayList<Event>();
        for(Event e : events){
            if(e.getUser().equals(creatorID)){
                myHostedEvents.add(e);
            }
        }
        return myHostedEvents;
    }

}
