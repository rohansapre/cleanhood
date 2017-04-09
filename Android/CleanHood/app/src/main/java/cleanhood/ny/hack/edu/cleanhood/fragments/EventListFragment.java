package cleanhood.ny.hack.edu.cleanhood.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cleanhood.ny.hack.edu.cleanhood.R;

/**
 * Created by vaibhavshukla on 4/8/17.
 */

public class EventListFragment extends Fragment {

    private RecyclerView mEventListView;
    private View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.event_list_fragment, container, false);
        return rootView;
    }
}
