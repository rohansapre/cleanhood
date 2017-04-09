package cleanhood.ny.hack.edu.cleanhood.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import cleanhood.ny.hack.edu.cleanhood.R;
import cleanhood.ny.hack.edu.cleanhood.activities.LandingActivity;
import cleanhood.ny.hack.edu.cleanhood.adapters.EventListAdapter;
import cleanhood.ny.hack.edu.cleanhood.utilities.EventList;
import cleanhood.ny.hack.edu.cleanhood.valueObjects.Event;

/**
 * Created by vaibhavshukla on 4/8/17.
 */

public class EventListFragment extends Fragment {

    private ListView mEventListView;
    private View rootView;
    private List<Event> mEventList;
    private Spinner mSpinner;
    EventListAdapter eventListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventList = EventList.getInstance().getEvents();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((LandingActivity) getActivity()).showFAB();
        rootView = inflater.inflate(R.layout.event_list_fragment, container, false);
        mEventListView = (ListView) rootView.findViewById(R.id.event_list);
        //populateDummyEvent();
         eventListAdapter = new EventListAdapter(mEventList,getActivity());
        mEventListView.setAdapter(eventListAdapter);
        addItemsOnSpinner();
        return rootView;
    }



    public void populateDummyEvent()
    {

        for(int i=0;i<5;i++)
        {
            Event e = new Event();
            e.setName("Event"+i);
            mEventList.add(e);
        }
        for(int i=5;i<10;i++)
        {
            Event e = new Event();
            e.setName("Event"+i);
            e.setClosed(true);
            mEventList.add(e);
        }
    }


    public void addItemsOnSpinner() {

        mSpinner = (Spinner) rootView.findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("All Events");
        list.add("Upcoming Events");
        list.add("Past Events");
        list.add("My Hosted Events");

        // Creating adapter for spinner
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);



        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mSpinner.setAdapter(dataAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                    mEventList = EventList.getInstance().getEvents();
                else if (i==1)
                    mEventList = EventList.getInstance().getUpcomingEvents();
                else if (i==2)
                    mEventList = EventList.getInstance().getPastEvents();
                else
                    mEventList = EventList.getInstance().getMyHostedEvents("58e9f6867023c4b34d86343f");
                ((LandingActivity) getActivity()).currentSpin = i%4;
                eventListAdapter.setmEventList(mEventList);
                eventListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
