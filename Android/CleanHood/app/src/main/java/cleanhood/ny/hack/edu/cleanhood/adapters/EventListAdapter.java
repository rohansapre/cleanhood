package cleanhood.ny.hack.edu.cleanhood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cleanhood.ny.hack.edu.cleanhood.R;
import cleanhood.ny.hack.edu.cleanhood.activities.LandingActivity;
import cleanhood.ny.hack.edu.cleanhood.fragments.EventDetailsFragment;
import cleanhood.ny.hack.edu.cleanhood.fragments.EventDetailsFragmentHost;
import cleanhood.ny.hack.edu.cleanhood.fragments.EventDetailsFragmentJoin;
import cleanhood.ny.hack.edu.cleanhood.utilities.Constants;
import cleanhood.ny.hack.edu.cleanhood.valueObjects.Event;
import cleanhood.ny.hack.edu.cleanhood.utilities.ImageDownloaderTask;

/**
 * Created by vaibhavshukla on 4/9/17.
 */

public class EventListAdapter extends BaseAdapter {
    private List<Event> mEventList;
    private Context mContext;
    private TextView mEventname,mEventDes,mEventDate;
    private ImageView imageView;


    public EventListAdapter(List<Event> mEventList, Context mContext) {
        this.mEventList = mEventList;
        this.mContext = mContext;
    }

    public List<Event> getmEventList() {
        return mEventList;
    }

    public void setmEventList(List<Event> mEventList) {
        this.mEventList = mEventList;
    }

    @Override
    public int getCount() {
        return mEventList.size();
    }

    @Override
    public Object getItem(int i) {
        return mEventList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.event_list_item,viewGroup,false);
        imageView = (ImageView) v.findViewById(R.id.event_image);
        mEventname = (TextView) v.findViewById(R.id.event_name);
        mEventDes = (TextView) v.findViewById(R.id.event_desc);
        mEventDate = (TextView) v.findViewById(R.id.event_date);

        mEventname.setText(mEventList.get(i).getName());
        mEventDes.setText(mEventList.get(i).getPurpose());
        mEventDate.setText(mEventList.get(i).getDate());
        new ImageDownloaderTask(imageView).execute(mEventList.get(i).getInitialPicURL());
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LandingActivity)mContext).setmEvent(mEventList.get(i));
                switch(((LandingActivity)mContext).currentSpin){
                    case 0:
                        ((LandingActivity)mContext).replaceFragment((new EventDetailsFragment()), Constants.EVENT_DETAILS_FRAGMENT);
                        break;
                    case 1:
                        ((LandingActivity)mContext).replaceFragment((new EventDetailsFragmentJoin()), Constants.EVENT_DETAILS_FRAGMENT_JOIN);
                        break;
                    case 2:
                        ((LandingActivity)mContext).replaceFragment((new EventDetailsFragment()), Constants.EVENT_DETAILS_FRAGMENT);
                        break;
                    case 3:
                        ((LandingActivity)mContext).replaceFragment((new EventDetailsFragmentHost()), Constants.EVENT_DETAILS_FRAGMENT_HOST);
                        break;
                    default:
                        break;
                }

            }
        });
        return v;
    }
}
