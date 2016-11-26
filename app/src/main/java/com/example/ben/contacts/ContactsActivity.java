package com.example.ben.contacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(toolbar);

        ListView listView = (ListView)findViewById(R.id.listViewTasks);


        DBHelper db = DBHelper.getInstance(this);
        DBTask[] dbTasks = db.getTasks();

        ArrayAdapter<DBTask> arrayAdapter = new ArrayAdapter<DBTask>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                dbTasks);

        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
}
