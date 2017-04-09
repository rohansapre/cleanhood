package cleanhood.ny.hack.edu.cleanhood.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;

import cleanhood.ny.hack.edu.cleanhood.R;
import cleanhood.ny.hack.edu.cleanhood.fragments.EventListFragment;
import cleanhood.ny.hack.edu.cleanhood.utilities.Constants;
import cleanhood.ny.hack.edu.cleanhood.valueObjects.Event;

public class LandingActivity extends AppCompatActivity {
    private int mContainerId ;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager =getFragmentManager();
    private String mCurrentFragment;
    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    private Event mEvent;
    private FloatingActionButton fab;
    public int currentSpin = 0;

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


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photo));
                imageUri = Uri.fromFile(photo);
                startActivityForResult(intent, TAKE_PICTURE);

            }
        });
    }

    // camera callback method
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    //Uri selectedImage = imageUri;
                    Intent intent = new Intent(this, CreateEventActivity.class);
                    //intent.putExtra(Constants.IMAGE_URI, selectedImage);
                    startActivity(intent);
                }
        }
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

    public Event getmEvent(){
        return mEvent;
    }

    public void setmEvent(Event e){
        mEvent = e;
    }

    public void hideFAB(){
        fab.hide();
    }

    public void showFAB(){
        fab.show();
    }
}
