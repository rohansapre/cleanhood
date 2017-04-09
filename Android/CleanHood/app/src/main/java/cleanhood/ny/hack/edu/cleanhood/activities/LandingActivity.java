package cleanhood.ny.hack.edu.cleanhood.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cleanhood.ny.hack.edu.cleanhood.R;
import cleanhood.ny.hack.edu.cleanhood.fragments.EventListFragment;
import cleanhood.ny.hack.edu.cleanhood.utilities.Constants;

public class LandingActivity extends AppCompatActivity {
    private int mContainerId ;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager =getFragmentManager();
    private String mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContainerId = R.id.container_fragment;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(mContainerId, new EventListFragment(), Constants.EVENT_LIST_FRAGMENT);
        mCurrentFragment = Constants.EVENT_LIST_FRAGMENT;
        fragmentTransaction.commitAllowingStateLoss();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void replaceFragment(Fragment fragment, String tag) {

        try {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(mContainerId, fragment, tag);
            fragmentTransaction.addToBackStack(tag);
            mCurrentFragment = tag;
            fragmentTransaction.commitAllowingStateLoss();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
