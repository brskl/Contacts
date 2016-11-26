package com.example.ben.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBtnTasks(View view)
    {
        Intent explicitIntent = new Intent(this, ContactsActivity.class);
        startActivity(explicitIntent);

    }
}
