package com.sansa.locat;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    static String apList = "AP-LIST";
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        //Init text-view
        TextView textView = findViewById(R.id.textView);
        textView.setText(apList);
        //flush in 2s
        handler.postDelayed(runnable, 2000);
    }

    /** Called at the beginning, get signal info every 2 seconds.*/
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            showDetailMsg(apList);
            handler.postDelayed(this, 2000);//2s
        }
    };

    public void showDetailMsg(String msg) {
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(msg);
    }

    public void markDownAplist(View view) {
        // Do something in response to button
        // use SQLite
    }
}
