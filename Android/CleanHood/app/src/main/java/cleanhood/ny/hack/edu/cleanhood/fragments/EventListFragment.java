package cleanhood.ny.hack.edu.cleanhood.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cleanhood.ny.hack.edu.cleanhood.R;
import cleanhood.ny.hack.edu.cleanhood.adapters.EventListAdapter;
import cleanhood.ny.hack.edu.cleanhood.valueObjects.Event;

/**
 * Created by vaibhavshukla on 4/8/17.
 */

public class EventListFragment extends Fragment {

    private ListView mEventListView;
    private View rootView;
    private List<Event> mEventList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventList = new ArrayList<Event>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.event_list_fragment, container, false);
        mEventListView = (ListView) rootView.findViewById(R.id.event_list);
        populateDummyEvent();
        EventListAdapter eventListAdapter = new EventListAdapter(mEventList,getActivity());
        mEventListView.setAdapter(eventListAdapter);
        return rootView;
    }



    public void populateDummyEvent()
    {

        for(int i=0;i<10;i++)
        {
            Event e = new Event();
            mEventList.add(e);
        }
    }
}
