package cleanhood.ny.hack.edu.cleanhood.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import cleanhood.ny.hack.edu.cleanhood.R;

public class CreateEventActivity extends AppCompatActivity {
    private File photo;
    private ImageView mImageView;
    private String TAG = "CAMERAERROR: ";
    private String KEY_IMAGE = "eImage";
    private String KEY_NAME = "eventpic.jpg";
    private String URL = "http://172.30.20.123:300/api/upload";
    private Bitmap bitmap;
    private String eventId;
    private String CREATE_EVENT_URL = "http://172.30.20.123:300/api/event";
    private EditText mEventName,mEventDescription;
            private DatePicker mdate;
    private TimePicker mTime;
    private Button mCreateEvent;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mEventName  = (EditText) findViewById(R.id.enter_event_name);
        mEventDescription = (EditText) findViewById(R.id.enter_event_desc);
        mCreateEvent = (Button) findViewById(R.id.create_event);
        mdate = (DatePicker) findViewById(R.id.enter_event_date);
        mTime = (TimePicker) findViewById(R.id.enter_event_time);
        mCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading= ProgressDialog.show(CreateEventActivity.this,"Creating Event...","Please wait...",false,false);
                createEvent();
            }
        });

        if (isStoragePermissionGranted()){
            accessPic();
        }

    }

    public void accessPic(){
        System.out.println("ACCESSPIC called");
        photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        Uri selectedImage = Uri.fromFile(photo);

        getContentResolver().notifyChange(selectedImage, null);

        ContentResolver cr = getContentResolver();
        try {
            bitmap = android.provider.MediaStore.Images.Media
                    .getBitmap(cr, selectedImage);

            mImageView.setImageBitmap(bitmap);

        } catch (Exception e) {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                    .show();
            Log.e("Camera", e.toString());
        }
    }



    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                System.out.println("REQUESTING PERMISSION");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else{
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
            accessPic();
        }
    }



    private void uploadImage(){
        //Showing the progress dialog

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
                        loading.dismiss();
                        //Showing toast message of the response
                        Toast.makeText(CreateEventActivity.this, s , Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Dismissing the progress dialog
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(CreateEventActivity.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Converting Bitmap to String
                String image = getStringImage(bitmap);

                //Getting Image Name


                //Creating parameters
                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put("eid",eventId);

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    public void createEvent()
    {
        StringRequest postRequest = new StringRequest(Request.Method.POST, CREATE_EVENT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response).getJSONObject("form");
                            String site = jsonResponse.getString("site"),
                                    network = jsonResponse.getString("network");
                            System.out.println("Site: "+site+"\nNetwork: "+network);
                            eventId =response;
                            uploadImage();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
                Date date= (Date) new Date
                        (mdate.getYear(), mdate.getMonth(), mdate.getDayOfMonth());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = sdf.format(date);


                String s;
                Format formatter;
                Calendar calendar = Calendar.getInstance();
// tp = TimePicker
                calendar.set(Calendar.HOUR_OF_DAY, mTime.getCurrentHour());
                calendar.set(Calendar.MINUTE, mTime.getCurrentMinute());
                calendar.clear(Calendar.SECOND); //reset seconds to zero

                formatter = new SimpleDateFormat("HH:mm:ss");
                s = formatter.format(calendar.getTime());

                Map<String, String>  params = new HashMap<>();
                // the POST parameters:
                params.put("name", mEventName.getText().toString());
                params.put("purpose", mEventDescription.getText().toString());
                params.put("date", dateString);
                params.put("time", s);
                params.put("location", "New York, USA");
                params.put("_user", "58e9f6867023c4b34d86343f");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
    }
}
