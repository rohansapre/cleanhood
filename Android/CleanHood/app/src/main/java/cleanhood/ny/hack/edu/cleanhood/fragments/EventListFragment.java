package cleanhood.ny.hack.edu.cleanhood.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventList = new ArrayList<Event>();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((LandingActivity) getActivity()).showFAB();
        rootView = inflater.inflate(R.layout.event_list_fragment, container, false);
        mEventListView = (ListView) rootView.findViewById(R.id.event_list);
        populateDummyEvent();
        EventListAdapter eventListAdapter = new EventListAdapter(mEventList,getActivity());
        mEventListView.setAdapter(eventListAdapter);
        addItemsOnSpinner();
        return rootView;
    }



    public void populateDummyEvent()
    {

        for(int i=0;i<5;i++)
        {
            Event e = new Event();
            e.setEventName("Event"+i);
            mEventList.add(e);
        }
        for(int i=5;i<10;i++)
        {
            Event e = new Event();
            e.setEventName("Event"+i);
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
    }
}
