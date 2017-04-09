package cleanhood.ny.hack.edu.cleanhood.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cleanhood.ny.hack.edu.cleanhood.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }
}
