package cleanhood.ny.hack.edu.cleanhood.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cleanhood.ny.hack.edu.cleanhood.R;
import cleanhood.ny.hack.edu.cleanhood.activities.LandingActivity;
import cleanhood.ny.hack.edu.cleanhood.valueObjects.Event;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailsFragmentJoin extends Fragment {

    private Event mEvent;

    public EventDetailsFragmentJoin() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvent = ((LandingActivity) getActivity()).getmEvent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((LandingActivity) getActivity()).hideFAB();
        View v = inflater.inflate(R.layout.fragment_event_details_join, container, false);
        TextView tv_name = (TextView) v.findViewById(R.id.event_details_name);
        tv_name.setText(mEvent.getName());
        TextView tv_desc = (TextView) v.findViewById(R.id.event_details_desc);
        tv_desc.setText(mEvent.getPurpose());
        TextView tv_loc = (TextView) v.findViewById(R.id.event_details_location);
        tv_loc.setText(mEvent.getLocation());
        TextView tv_date = (TextView) v.findViewById(R.id.event_details_date);
        tv_date.setText(mEvent.getDate());
        TextView tv_time = (TextView) v.findViewById(R.id.event_details_time);
        tv_time.setText(mEvent.getTime());

        Button bt = (Button) v.findViewById(R.id.btn_participate);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Register User to the event

            }
        });

        return v;
    }



}
