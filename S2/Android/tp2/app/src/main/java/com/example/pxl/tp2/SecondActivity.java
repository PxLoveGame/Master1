package com.example.pxl.tp2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends Activity {
    List<CharSequence> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        results.add("Prénom : " + getIntent().getExtras().getString("firstname") + " Nom : " + getIntent().getExtras().getString("lastname") + " téléphone : " + getIntent().getExtras().getString("phone"));

        ListView listView = findViewById(R.id.listView);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                results);

        listView.setAdapter(adapter);
    }
}
