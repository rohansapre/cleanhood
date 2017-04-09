package cleanhood.ny.hack.edu.cleanhood.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import cleanhood.ny.hack.edu.cleanhood.R;
import cleanhood.ny.hack.edu.cleanhood.utilities.Constants;
import cleanhood.ny.hack.edu.cleanhood.utilities.EventList;
import cleanhood.ny.hack.edu.cleanhood.valueObjects.Event;

public class SplashActivity extends AppCompatActivity {

    private ImageView mLogo;
    private TextView mLoadingText;

    public String userName;

    public String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        checkUser();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        mLogo = (ImageView) findViewById(R.id.app_logo);
        mLoadingText = (TextView) findViewById(R.id.loading_text);
        scaleLogo();

    }

    public void checkUser()
    {
        SharedPreferences prefs = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        userName = prefs.getString(Constants.USER_NAME, null);//"No name defined" is the default value.
        email = prefs.getString(Constants.USER_EMAIL, null); //0 is the default value.
    }


    public void scaleLogo()
    {
        Animation scale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
// 1 second duration
        scale.setDuration(2000);
// Moving up

// Launching animation set


        Animation.AnimationListener animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLoadingText.setVisibility(View.VISIBLE);
                fetchEvents();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        scale.setAnimationListener(animationListener);

        mLogo.startAnimation(scale);
    }



    public void startHandler()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                if(userName==null || userName.equals("") ){
                    Intent i = new Intent(SplashActivity.this,LandingActivity.class);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(SplashActivity.this,LandingActivity.class);
                    startActivity(i);
                }
            }
        },2000);
    }


    public void fetchEvents() {
        String url = "http://172.30.20.123:3000/api/event";

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            Gson gson = new Gson();
                            JSONArray x =  response.getJSONArray("eventList");
                            for (int i = 0;i<x.length();i++) {
                                JsonParser parser = new JsonParser();
                                JsonElement mJson =  parser.parse(x.get(i).toString());
                                Event object = gson.fromJson(mJson, Event.class);
                                EventList.getInstance().getEvents().add(object);

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        finish();
                        if(userName==null || userName.equals("") ){
                            Intent i = new Intent(SplashActivity.this,LandingActivity.class);
                            startActivity(i);
                        }
                        else{
                            Intent i = new Intent(SplashActivity.this,LandingActivity.class);
                            startActivity(i);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(SplashActivity.this,"Error response",Toast.LENGTH_LONG).show();
                        finish();
                        if(userName==null || userName.equals("") ){
                            Intent i = new Intent(SplashActivity.this,LandingActivity.class);
                            startActivity(i);
                        }
                        else{
                            Intent i = new Intent(SplashActivity.this,LandingActivity.class);
                            startActivity(i);
                        }
                    }
                });

        Volley.newRequestQueue(this).add(jsonRequest);
    }
}

