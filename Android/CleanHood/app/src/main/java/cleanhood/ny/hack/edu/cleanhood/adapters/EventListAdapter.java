package cleanhood.ny.hack.edu.cleanhood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cleanhood.ny.hack.edu.cleanhood.R;
import cleanhood.ny.hack.edu.cleanhood.valueObjects.Event;

/**
 * Created by vaibhavshukla on 4/9/17.
 */

public class EventListAdapter extends BaseAdapter {
    private List<Event> mEventList;
    private Context mContext;


    public EventListAdapter(List<Event> mEventList, Context mContext) {
        this.mEventList = mEventList;
        this.mContext = mContext;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.event_list_item,viewGroup,false);
        return v;
    }
}
