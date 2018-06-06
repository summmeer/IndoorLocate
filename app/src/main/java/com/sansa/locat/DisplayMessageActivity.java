package com.sansa.locat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

public class DisplayMessageActivity extends AppCompatActivity {

    static String apList = "AP-LIST";
    Handler handler=new Handler();

    final static String[] wifiName = {"ac:a3:1e:e8:61:20","ac:a3:1e:e8:61:21","ac:a3:1e:e8:61:22","ac:a3:1e:e8:61:23","ac:a3:1e:e8:61:25","ac:a3:1e:e8:61:26","ac:a3:1e:e8:62:20","ac:a3:1e:e8:62:21","ac:a3:1e:e8:62:22","ac:a3:1e:e8:62:23","ac:a3:1e:e8:62:25","ac:a3:1e:e8:62:26","ac:a3:1e:e8:63:00","ac:a3:1e:e8:63:01","ac:a3:1e:e8:63:02","ac:a3:1e:e8:63:03","ac:a3:1e:e8:63:05","ac:a3:1e:e8:63:06","ac:a3:1e:e8:63:60","ac:a3:1e:e8:63:61","ac:a3:1e:e8:63:62","ac:a3:1e:e8:63:63","ac:a3:1e:e8:63:65","ac:a3:1e:e8:63:66","ac:a3:1e:f1:ed:60","ac:a3:1e:f1:ed:61","ac:a3:1e:f1:ed:62","ac:a3:1e:f1:ed:63","ac:a3:1e:f1:ed:65","ac:a3:1e:f1:ed:66","ac:a3:1e:f2:03:80","ac:a3:1e:f2:03:81","ac:a3:1e:f2:03:82","ac:a3:1e:f2:03:83","ac:a3:1e:f2:03:85","ac:a3:1e:f2:03:86","ac:a3:1e:f2:44:60","ac:a3:1e:f2:44:61","ac:a3:1e:f2:44:62","ac:a3:1e:f2:44:63","ac:a3:1e:f2:44:65","ac:a3:1e:f2:44:66","ac:a3:1e:f2:4a:60","ac:a3:1e:f2:4a:61","ac:a3:1e:f2:4a:62","ac:a3:1e:f2:4a:63","ac:a3:1e:f2:4a:65","ac:a3:1e:f2:4a:66","ac:a3:1e:f2:4d:80","ac:a3:1e:f2:4d:81","ac:a3:1e:f2:4d:82","ac:a3:1e:f2:4d:83","ac:a3:1e:f2:4d:85","ac:a3:1e:f2:4d:86","ac:a3:1e:f2:4e:a0","ac:a3:1e:f2:4e:a1","ac:a3:1e:f2:4e:a2","ac:a3:1e:f2:4e:a3","ac:a3:1e:f2:4e:a5","ac:a3:1e:f2:4e:a6","ac:a3:1e:f2:52:40","ac:a3:1e:f2:52:41","ac:a3:1e:f2:52:42","ac:a3:1e:f2:52:43","ac:a3:1e:f2:52:45","ac:a3:1e:f2:52:46","ac:a3:1e:f2:58:80","ac:a3:1e:f2:58:81","ac:a3:1e:f2:58:82","ac:a3:1e:f2:58:83","ac:a3:1e:f2:58:85","ac:a3:1e:f2:58:86","ac:a3:1e:f2:5a:40","ac:a3:1e:f2:5a:41","ac:a3:1e:f2:5a:42","ac:a3:1e:f2:5a:43","ac:a3:1e:f2:5a:45","ac:a3:1e:f2:5a:46","ac:a3:1e:f2:5a:e0","ac:a3:1e:f2:5a:e1","ac:a3:1e:f2:5a:e2","ac:a3:1e:f2:5a:e3","ac:a3:1e:f2:5a:e5","ac:a3:1e:f2:5a:e6","ac:a3:1e:f2:5b:20","ac:a3:1e:f2:5b:21","ac:a3:1e:f2:5b:22","ac:a3:1e:f2:5b:23","ac:a3:1e:f2:5b:25","ac:a3:1e:f2:5b:26","ac:a3:1e:f2:5e:20","ac:a3:1e:f2:5e:21","ac:a3:1e:f2:5e:22","ac:a3:1e:f2:5e:23","ac:a3:1e:f2:5e:25","ac:a3:1e:f2:5e:26","ac:a3:1e:f2:5f:40","ac:a3:1e:f2:5f:41","ac:a3:1e:f2:5f:42","ac:a3:1e:f2:5f:43","ac:a3:1e:f2:5f:45","ac:a3:1e:f2:5f:46","ac:a3:1e:f2:60:80","ac:a3:1e:f2:60:81","ac:a3:1e:f2:60:82","ac:a3:1e:f2:60:83","ac:a3:1e:f2:60:85","ac:a3:1e:f2:60:86","ac:a3:1e:f2:60:e0","ac:a3:1e:f2:60:e1","ac:a3:1e:f2:60:e2","ac:a3:1e:f2:60:e3","ac:a3:1e:f2:60:e5","ac:a3:1e:f2:60:e6","ac:a3:1e:f2:65:20","ac:a3:1e:f2:65:21","ac:a3:1e:f2:65:22","ac:a3:1e:f2:65:23","ac:a3:1e:f2:65:25","ac:a3:1e:f2:65:26"};
    final static String [] levelName ={"LEVEL1","LEVEL2","LEVEL3","LEVEL4","LEVEL5","LEVEL6","LEVEL7","LEVEL8","LEVEL9","LEVEL10","LEVEL11","LEVEL12","LEVEL13","LEVEL14","LEVEL15","LEVEL16","LEVEL17","LEVEL18","LEVEL19","LEVEL20","LEVEL21","LEVEL22","LEVEL23","LEVEL24","LEVEL25","LEVEL26","LEVEL27","LEVEL28","LEVEL29","LEVEL30","LEVEL31","LEVEL32","LEVEL33","LEVEL34","LEVEL35","LEVEL36","LEVEL37","LEVEL38","LEVEL39","LEVEL40","LEVEL41","LEVEL42","LEVEL43","LEVEL44","LEVEL45","LEVEL46","LEVEL47","LEVEL48","LEVEL49","LEVEL50","LEVEL51","LEVEL52","LEVEL53","LEVEL54","LEVEL55","LEVEL56","LEVEL57","LEVEL58","LEVEL59","LEVEL60","LEVEL61","LEVEL62","LEVEL63","LEVEL64","LEVEL65","LEVEL66","LEVEL67","LEVEL68","LEVEL69","LEVEL70","LEVEL71","LEVEL72","LEVEL73","LEVEL74","LEVEL75","LEVEL76","LEVEL77","LEVEL78","LEVEL79","LEVEL80","LEVEL81","LEVEL82","LEVEL83","LEVEL84","LEVEL85","LEVEL86","LEVEL87","LEVEL88","LEVEL89","LEVEL90","LEVEL91","LEVEL92","LEVEL93","LEVEL94","LEVEL95","LEVEL96","LEVEL97","LEVEL98","LEVEL99","LEVEL100","LEVEL101","LEVEL102","LEVEL103","LEVEL104","LEVEL105","LEVEL106","LEVEL107","LEVEL108","LEVEL109","LEVEL110","LEVEL111","LEVEL112","LEVEL113","LEVEL114","LEVEL115","LEVEL116","LEVEL117","LEVEL118","LEVEL119","LEVEL120"};
    MySQLiteOpenHelper dbHelper;
    private SQLiteDatabase wifiDatabase;
    private int id=0;
    List<ScanResult> apList_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        //Init text-view
        TextView textView = findViewById(R.id.textView);
        textView.setText(apList);
        //database
        dbHelper = new MySQLiteOpenHelper(this,"WifiDatabase.db");
        wifiDatabase = dbHelper.getWritableDatabase();
        //flush in 2s
        handler.postDelayed(runnable, 1000);
    }

    /** Called at the beginning, get signal info every 2 seconds.*/
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            showDetailMsg(apList);
            apList_result = MainActivity.apList;
            handler.postDelayed(this, 1000);//2s
        }
    };

    public void DeleteDatebase(View view) {
//        wifiDatabase.execSQL("DELETE FROM user");  //clear the data
//        wifiDatabase.execSQL("update sqlite_sequence SET seq = 0 where name ='user'");  //clear ID
//        id = 1;
    }

    public void showDetailMsg(String msg) {
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(msg);
    }

    public void markDownAplist(View view) {
        // Do something in response to button
        // use SQLite
        // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
        Cursor cursor = wifiDatabase.rawQuery("select * from user", null);

        EditText Locate_x = findViewById(R.id.Locate_x);
        EditText Locate_y = findViewById(R.id.Locate_y);
        int x = Integer.parseInt(Locate_x.getText().toString());
        int y = Integer.parseInt(Locate_y.getText().toString());

        for(int i=0; i<96; i++){
            cursor.moveToNext();
            int x_base = cursor.getInt(1); //x
            int y_base = cursor.getInt(2); //y
            if(x_base == x && y_base == y){
                break;
            }
        }
        //if (apList.length() <= 20) return;

        double[] wifiVector = new double[120];
        for(int i=0; i<120; i++) wifiVector[i] = 0;
        for(int i=0; i<apList_result.size(); i++){
            String name = apList_result.get(i).BSSID;
            for(int j=0; j<120; j++){
                if(wifiName[j].equals(name)){
                    wifiVector[j] = apList_result.get(i).level + 100;
                }
            }
        }

        ContentValues values = new ContentValues();

        //  values.put("id", cursor.getInt(0));
        values.put("x", x);
        values.put("y", y);

        for(int i=0; i<120; i++){
            double base_data =  Double.parseDouble(cursor.getString(i+3));
            wifiVector[i] = (wifiVector[i] + base_data)/2;
            BigDecimal b = new BigDecimal(wifiVector[i]);
            wifiVector[i] = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            Double dou_obj = new Double(wifiVector[i]);
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            String level_last = nf.format(dou_obj);
            values.put(levelName[i],level_last);
        }

        //    String[] whereArgs = {Integer.toString(cursor.getInt(0))};
        wifiDatabase.update("user", values, "id =" + cursor.getInt(0),null);

        TextView SuccessSave = findViewById(R.id.SuccessSave);
        SuccessSave.setText("Update Successfully!");
        SuccessSave.setTextColor(Color.rgb(60,179,113));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView SuccessSave = findViewById(R.id.SuccessSave);
                SuccessSave.setText("");
            }
        }, 2000);//3秒后执行Runnable中的run方法

        return;
    }
}
