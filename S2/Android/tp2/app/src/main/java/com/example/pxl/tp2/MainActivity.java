package com.example.pxl.tp2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private int compteur = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button validate = findViewById(R.id.button);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                intent.putExtra("firstname",((EditText) findViewById(R.id.editText)).getText().toString()).
                        putExtra("lastname",((EditText) findViewById(R.id.editText2)).getText().toString()).
                        putExtra("phone", ((EditText) findViewById(R.id.editText3)).getText().toString());

                startActivity(intent);
            }
        });
    }

    protected void onResume(){
        super.onResume();
        ((TextView) findViewById(R.id.textView4)).setText("Nombres de visites : " + compteur++);
    }
}
