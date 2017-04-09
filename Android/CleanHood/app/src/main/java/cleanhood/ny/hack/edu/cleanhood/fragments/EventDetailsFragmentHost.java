package cleanhood.ny.hack.edu.cleanhood.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cleanhood.ny.hack.edu.cleanhood.R;
import cleanhood.ny.hack.edu.cleanhood.activities.LandingActivity;
import cleanhood.ny.hack.edu.cleanhood.valueObjects.Event;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailsFragmentHost extends Fragment {

    private Event mEvent;
    private String URL = "http://172.30.20.123:3000/api/sendMessage";
     EditText twilioText;

    public EventDetailsFragmentHost() {
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
        View v = inflater.inflate(R.layout.fragment_event_details_host, container, false);
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

        twilioText = (EditText) v.findViewById(R.id.twilioText);


        Button tb = (Button) v.findViewById(R.id.sendTwilio);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = twilioText.getText().toString();
                sendTwilioText(s, mEvent.getName());
            }
        });

        return v;
    }


    public void sendTwilioText(final String num,final String eventName){
        // POST method here
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                            JSONObject jsonResponse = new JSONObject(response).getJSONObject("form");
//                            String site = jsonResponse.getString("site"),
//                                    network = jsonResponse.getString("network");
//                            System.out.println("Site: "+site+"\nNetwork: "+network);
                        Toast.makeText(getActivity(),"Invitation successfully sent",Toast.LENGTH_LONG).show();
                        twilioText.setText("");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {


                Map<String, String>  params = new HashMap<>();
                // the POST parameters:

                params.put("num", num);
                params.put("event",eventName);
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(postRequest);
    }

}
