package com.sansa.locat;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private WifiScanReceiver wifiReceiver;
    protected static final String ACTIVITY_TAG="MyAndroidDisplay";
    //about xml
    private ImageView animationIV;
    private ImageView locateBook;
    private TextView mAccText;
    private TextView searchBook;
    private AnimationDrawable animationDrawable;
    Handler handler=new Handler();
    //about sensor
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float[] sensorVal;
    //about draw map
    private final double  x_bias = 110;
    private final double y_bias = 1425;
    private final double x_scale = 94.375;
    private final double y_scale = -87.92;
    private int x_px = 580;
    private int y_px = 1425;
    //about DB
    private double Locate_x = 100;
    private double Locate_y = 100;
    MySQLiteOpenHelper dbHelper;
    SQLiteDatabase wifiDatabase;
    static List<ScanResult> apList;
    int numOfWifi = 120;
    int numOfx = 9;
    int numOfy = 14;
    final static String[] wifiName = {"ac:a3:1e:e8:61:20","ac:a3:1e:e8:61:21","ac:a3:1e:e8:61:22","ac:a3:1e:e8:61:23","ac:a3:1e:e8:61:25","ac:a3:1e:e8:61:26","ac:a3:1e:e8:62:20","ac:a3:1e:e8:62:21","ac:a3:1e:e8:62:22","ac:a3:1e:e8:62:23","ac:a3:1e:e8:62:25","ac:a3:1e:e8:62:26","ac:a3:1e:e8:63:00","ac:a3:1e:e8:63:01","ac:a3:1e:e8:63:02","ac:a3:1e:e8:63:03","ac:a3:1e:e8:63:05","ac:a3:1e:e8:63:06","ac:a3:1e:e8:63:60","ac:a3:1e:e8:63:61","ac:a3:1e:e8:63:62","ac:a3:1e:e8:63:63","ac:a3:1e:e8:63:65","ac:a3:1e:e8:63:66","ac:a3:1e:f1:ed:60","ac:a3:1e:f1:ed:61","ac:a3:1e:f1:ed:62","ac:a3:1e:f1:ed:63","ac:a3:1e:f1:ed:65","ac:a3:1e:f1:ed:66","ac:a3:1e:f2:03:80","ac:a3:1e:f2:03:81","ac:a3:1e:f2:03:82","ac:a3:1e:f2:03:83","ac:a3:1e:f2:03:85","ac:a3:1e:f2:03:86","ac:a3:1e:f2:44:60","ac:a3:1e:f2:44:61","ac:a3:1e:f2:44:62","ac:a3:1e:f2:44:63","ac:a3:1e:f2:44:65","ac:a3:1e:f2:44:66","ac:a3:1e:f2:4a:60","ac:a3:1e:f2:4a:61","ac:a3:1e:f2:4a:62","ac:a3:1e:f2:4a:63","ac:a3:1e:f2:4a:65","ac:a3:1e:f2:4a:66","ac:a3:1e:f2:4d:80","ac:a3:1e:f2:4d:81","ac:a3:1e:f2:4d:82","ac:a3:1e:f2:4d:83","ac:a3:1e:f2:4d:85","ac:a3:1e:f2:4d:86","ac:a3:1e:f2:4e:a0","ac:a3:1e:f2:4e:a1","ac:a3:1e:f2:4e:a2","ac:a3:1e:f2:4e:a3","ac:a3:1e:f2:4e:a5","ac:a3:1e:f2:4e:a6","ac:a3:1e:f2:52:40","ac:a3:1e:f2:52:41","ac:a3:1e:f2:52:42","ac:a3:1e:f2:52:43","ac:a3:1e:f2:52:45","ac:a3:1e:f2:52:46","ac:a3:1e:f2:58:80","ac:a3:1e:f2:58:81","ac:a3:1e:f2:58:82","ac:a3:1e:f2:58:83","ac:a3:1e:f2:58:85","ac:a3:1e:f2:58:86","ac:a3:1e:f2:5a:40","ac:a3:1e:f2:5a:41","ac:a3:1e:f2:5a:42","ac:a3:1e:f2:5a:43","ac:a3:1e:f2:5a:45","ac:a3:1e:f2:5a:46","ac:a3:1e:f2:5a:e0","ac:a3:1e:f2:5a:e1","ac:a3:1e:f2:5a:e2","ac:a3:1e:f2:5a:e3","ac:a3:1e:f2:5a:e5","ac:a3:1e:f2:5a:e6","ac:a3:1e:f2:5b:20","ac:a3:1e:f2:5b:21","ac:a3:1e:f2:5b:22","ac:a3:1e:f2:5b:23","ac:a3:1e:f2:5b:25","ac:a3:1e:f2:5b:26","ac:a3:1e:f2:5e:20","ac:a3:1e:f2:5e:21","ac:a3:1e:f2:5e:22","ac:a3:1e:f2:5e:23","ac:a3:1e:f2:5e:25","ac:a3:1e:f2:5e:26","ac:a3:1e:f2:5f:40","ac:a3:1e:f2:5f:41","ac:a3:1e:f2:5f:42","ac:a3:1e:f2:5f:43","ac:a3:1e:f2:5f:45","ac:a3:1e:f2:5f:46","ac:a3:1e:f2:60:80","ac:a3:1e:f2:60:81","ac:a3:1e:f2:60:82","ac:a3:1e:f2:60:83","ac:a3:1e:f2:60:85","ac:a3:1e:f2:60:86","ac:a3:1e:f2:60:e0","ac:a3:1e:f2:60:e1","ac:a3:1e:f2:60:e2","ac:a3:1e:f2:60:e3","ac:a3:1e:f2:60:e5","ac:a3:1e:f2:60:e6","ac:a3:1e:f2:65:20","ac:a3:1e:f2:65:21","ac:a3:1e:f2:65:22","ac:a3:1e:f2:65:23","ac:a3:1e:f2:65:25","ac:a3:1e:f2:65:26"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyDB();
        initReceiver();
        initAcc();
        initAniIV();
        handler.postDelayed(runnable, 1000);//flush 1s
    }

    /** Called at the beginning, scan-wifi signal every 1 seconds.*/
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            openWifi();
            Locate();
            setPoint(Locate_x, Locate_y);
            showSensor();
            handler.postDelayed(this, 1000);//1s
        }
    };

    private void copyDB() {
        //data/data/packageName/databases/
        File mkdir = new File(getFilesDir().getParent(),"databases");
        //创建 databases文件夹
        if (!mkdir.exists()) mkdir.mkdirs();
        //数据库文件
        File file = new File(mkdir,"WifiDatabase.db");
        //只是在程序第一次启动时创建
        if(!file.exists()){
            AssetManager assets = getAssets();
            //执行文件复制
            try {
                InputStream open = assets.open("WifiDatabase.db");
                FileOutputStream fos = new FileOutputStream(file);
                byte[] bs = new byte[1024];
                int len ;
                while ((len = open.read(bs))!=-1){
                    fos.write(bs,0,len);
                }
                fos.flush();
                fos.close();
                open.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dbHelper = new MySQLiteOpenHelper(this,"WifiDatabase.db");
        wifiDatabase = dbHelper.getWritableDatabase();
    }

    /** Called when the user taps the Send button, go to a new page. */
    public void showDetail(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }

    public void setPoint(double x_pos, double y_pos) {
        x_px = (int)(x_pos * x_scale + x_bias);
        y_px = (int)(y_pos * y_scale + y_bias);
        animationIV.setX(x_px);
        animationIV.setY(y_px);
    }

    public void showBook(View view) {
        searchBook = (TextView) findViewById(R.id.bookName);
        String book_name = searchBook.getText().toString();
        //about string
        Log.i(MainActivity.ACTIVITY_TAG, book_name);
        int x = -100, y = -100;

        if(book_name.substring(1,2).equals("B")) {
            x = 320;
            y = 850;
        }
        if(book_name.substring(1,2).equals("M")) {
            x = 530;
            y = 500;
        }
        if(book_name.substring(1,2).equals("H")) {
            x = 710;
            y = 450;
        }
        if(book_name.substring(1,2).equals("G")) {
            x = 710;
            y = 800;
        }

        Log.i(MainActivity.ACTIVITY_TAG, String.valueOf(x));
        Log.i(MainActivity.ACTIVITY_TAG, String.valueOf(y));
        locateBook = (ImageView) findViewById(R.id.book_locate);
        locateBook.setX(x);
        locateBook.setY(y);
    }

    /**LOCATE*/
    public void Locate(){

        //Cursor cursor = wifiDatabase.rawQuery("select * from user", null);
        //TextView X_now = findViewById(R.id.x_now);
        //TextView Y_now = findViewById(R.id.y_now);

        double[] wifiVector = new double[120];
        for(int i=0; i<120; i++) wifiVector[i] = 0;
        for(int i=0; i<apList.size(); i++){
            String name = apList.get(i).BSSID;
            for(int j=0; j<120; j++){
                if(wifiName[j].equals(name)){
                    wifiVector[j] = apList.get(i).level + 100;
                }
            }
        }

        int searchScope = 4; //Scope of find

        double distance_level[][] = new double[numOfx-1][numOfy-1];
        for(int i=0; i<numOfx-1; i++)
            for(int j=0; j<numOfy-1; j++)
                distance_level[i][j] = 0;
        int[][] isPosition={{0,0,0,0,1,1,1,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1,1,1,1,1,1,0},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {0,0,0,0,1,1,1,1,1,1,1,1,1,0},
                {0,0,0,0,1,1,1,1,1,1,1,1,1,0}};
        if(Locate_x == 100 || Locate_y == 100){
            //There is no people's location before
            Cursor cursor = wifiDatabase.rawQuery("select * from user", null);
            for(int i=0; i<96; i++) {
                cursor.moveToNext();
                int x = cursor.getInt(1);
                int y = cursor.getInt(2);
                if(x+1 < numOfx && y+1 < numOfy){
                    if(isPosition[x+1][y+1] == 1) { //其右上方的格子
                        for (int m = 0; m < numOfWifi; m++) {
                            String level = cursor.getString(m + 3);  //get the level of the wifiNum
                            distance_level[x][y] += Math.pow((Double.parseDouble(level) - wifiVector[m]), 2);
                        }
                    }
                }
                if(y - 1 >= 0 && x+1 < numOfx){
                    if(isPosition[x+1][y-1] == 1){ //其右下方的格子
                        for (int m = 0; m < numOfWifi; m++) {
                            String level = cursor.getString(m + 3);  //get the level of the wifiNum
                            distance_level[x][y-1] += Math.pow((Double.parseDouble(level) - wifiVector[m]), 2);
                        }
                    }
                }
                if(x - 1 >= 0 && y + 1 < numOfy){
                    if(isPosition[x-1][y+1] == 1){//其左上方的格子
                        for (int m = 0; m < numOfWifi; m++) {
                            String level = cursor.getString(m + 3);  //get the level of the wifiNum
                            distance_level[x-1][y] += Math.pow((Double.parseDouble(level) - wifiVector[m]), 2);
                        }
                    }
                }
                if(x - 1 >= 0 && y - 1 >= 0){ //其左下方的格子
                    for (int m = 0; m < numOfWifi; m++) {
                        String level = cursor.getString(m + 3);  //get the level of the wifiNum
                        distance_level[x-1][y-1] += Math.pow((Double.parseDouble(level) - wifiVector[m]), 2);
                    }

                }
            }
            //  X_now.setText(Integer.toString(count));
            double min_distance = Double.MAX_VALUE;
            for(int i = 0; i<numOfx-1; i++){
                for(int j=0; j<numOfy-1; j++){
                    if (isPosition[i][j] == 1 && isPosition[i+1][j+1] == 1 && isPosition[i+1][j] == 1 &&isPosition[i][j+1]==1){
                        if(distance_level[i][j] <= min_distance ) {
                            Locate_x = i+0.5;
                            Locate_y = j+0.5;
                            min_distance = distance_level[i][j];
                        }
                    }
                }
            }

        }
        else{
            //There is a location before
            int x_now = (int)Locate_x;
            int y_now = (int)Locate_y;
            //int count = 0;
            for(int i = -searchScope/2; i <= searchScope/2; i++){
                for(int j = -searchScope/2; j <= searchScope/2; j++){
                    Cursor cursor = wifiDatabase.rawQuery("select * from user", null);
                    if(x_now+i < 0 || y_now+j < 0 || x_now+i >8  || y_now+j>13 ) continue;
                    if(isPosition[x_now+i][y_now+j]==0) continue;
                    if(x_now+i+1 < numOfx && y_now+j+1 < numOfy){
                        if(i != searchScope/2 && j != searchScope/2 && isPosition[x_now+i+1][y_now+j+1]==1){//以其本身为顶点的正方形 右上方
                            int id=0;
                            if(y_now+j >= 4 && y_now+j <= 11)  id = 13 + (y_now+j - 4)*9 + x_now + i;
                            else{
                                for(int m=0; m<96; m++){
                                    cursor.moveToPosition(m);
                                    int x_base = cursor.getInt(1);
                                    int y_base = cursor.getInt(2);
                                    if(x_base == x_now+i &&y_base == y_now+j) {id = cursor.getInt(0);break;}
                                }
                            }

                            cursor.moveToPosition(id-1);
                            for (int m = 0; m < numOfWifi; m++) {
                                String level = cursor.getString(m + 3);  //get the level of the wifiNum
                                distance_level[x_now+i][y_now+j] += Math.pow((Double.parseDouble(level) - wifiVector[m]), 2);
                            }
                        }
                    }
                    if(x_now+i-1 >= 0 && y_now+j+1 < numOfy ){
                        if(i != -searchScope/2 && j != searchScope/2 && isPosition[x_now+i-1][y_now+j+1]==1) {//其左上方的格子
                            int id=0;

                            if(y_now+j >= 4 && y_now+j <= 11)  id = 13 + (y_now+j - 4)*9 + x_now + i;
                            else{
                                for(int m=0; m<96; m++){
                                    cursor.moveToPosition(m);
                                    int x_base = cursor.getInt(1);
                                    int y_base = cursor.getInt(2);
                                    if(x_base == x_now+i &&y_base == y_now+j) {id = cursor.getInt(0);break;}
                                }
                            }
                            if(id == 0) cursor.moveToFirst();
                            else cursor.moveToPosition(id-1);
                            for (int m = 0; m < numOfWifi; m++) {
                                String level = cursor.getString(m + 3);  //get the level of the wifiNum
                                distance_level[x_now+i-1][y_now+j] += Math.pow((Double.parseDouble(level) - wifiVector[m]), 2);
                            }
                        }
                    }
                    if(x_now+i-1 >=0 && y_now+j-1 >= 0){
                        if(i != -searchScope/2  && j != -searchScope/2  && isPosition[x_now+i-1][y_now+j-1]==1) { //其左下方的格子
                            //     count += 1;
                            int id=0;
                            if(y_now+j >= 4 && y_now+j <= 11)  id = 13 + (y_now+j - 4)*9 + x_now + i;
                            else{
                                for(int m=0; m<96; m++){
                                    cursor.moveToPosition(m);
                                    int x_base = cursor.getInt(1);
                                    int y_base = cursor.getInt(2);
                                    if(x_base == x_now+i &&y_base == y_now+j) {id = cursor.getInt(0);break;}
                                }
                            }
                            if(id == 0) cursor.moveToFirst();
                            else cursor.moveToPosition(id-1);
                            for (int m = 0; m < numOfWifi; m++) {
                                String level = cursor.getString(m + 3);  //get the level of the wifiNum
                                distance_level[x_now+i-1][y_now+j-1] += Math.pow((Double.parseDouble(level) - wifiVector[m]), 2);
                            }
                        }
                    }
                    if(x_now+i+1 < numOfx  && y_now+j-1 >= 0){
                        if(i !=  searchScope/2 && j != -searchScope/2 && isPosition[x_now+i+1][y_now+j-1]==1) { //其右下方的格子
                            int id=0;
                            if(y_now+j >= 4 && y_now+j <= 11)  id = 13 + (y_now+j - 4)*9 + x_now + i;
                            else{
                                for(int m=0; m<96; m++){
                                    cursor.moveToPosition(m);
                                    int x_base = cursor.getInt(1);
                                    int y_base = cursor.getInt(2);
                                    if(x_base == x_now+i &&y_base == y_now+j) {id = cursor.getInt(0);break;}
                                }
                            }
                            if(id == 0) cursor.moveToFirst();
                            else cursor.moveToPosition(id-1);
                            for (int m = 0; m < numOfWifi; m++) {
                                String level = cursor.getString(m + 3);  //get the level of the wifiNum
                                distance_level[x_now+i][y_now+j-1] += Math.pow((Double.parseDouble(level) - wifiVector[m]), 2);
                            }
                        }
                    }

                }

            }
            double min_distance= Double.MAX_VALUE;
            int i_final = 0;
            int j_final = 0;
            for(int i = -searchScope/2; i <= searchScope/2-1; i++) {
                for (int j = -searchScope / 2; j <= searchScope / 2-1; j++) {

                    if(x_now+i < 0 || y_now+j <=0 || x_now+i > numOfx-1 || y_now+j >= numOfy-1) continue;
                    if(x_now+i+1 > numOfx-1 || y_now+j+1 > numOfy-1) continue;
                    if (isPosition[x_now+i][y_now+j] == 1 && isPosition[x_now+i+1][y_now+j+1] == 1 && isPosition[x_now+i+1][y_now+j] == 1 && isPosition[x_now+i][y_now+j+1] == 1){
                        if(distance_level[x_now+i][y_now+j] <= min_distance ) {
                            i_final = i;
                            j_final = j;
                            min_distance = distance_level[x_now+i][y_now+j];

                        }
                    }
                }
            }

            //      X_now.setText(Integer.toString(count));
            Locate_x += i_final;
            Locate_y += j_final;
        }

//        Double dou_obj = new Double(Locate_x);
//        NumberFormat nf = NumberFormat.getInstance();
//        nf.setGroupingUsed(false);
//        String x_str = nf.format(dou_obj);
//        X_now.setText(x_str);
//        dou_obj = Double.valueOf(Locate_y);
//        String y_str = nf.format(dou_obj);
//        Y_now.setText(y_str);
    }

    /**Scan wifi ap-list use wifi-manager.*/
    public void openWifi() {
        WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!wifi.isWifiEnabled())wifi.setWifiEnabled(true); //Open wifi
        wifi.startScan();
        apList = wifi.getScanResults();
        DisplayMessageActivity.apList = wifiReceiver.ReadScan(apList).toString();
    }

    //Dynamic registration for wifi-scan-receiver
    private void initReceiver() {
        wifiReceiver = new WifiScanReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wifiReceiver, filter);
    }

    //Registration for acc
    private void initAcc() {
        // 获取传感器管理对象
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // 获取传感器的类型(TYPE_ACCELEROMETER:加速度传感器)
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 注册传感器监听函数
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
    }

    private void initAniIV(){

        //start animation
        animationIV = (ImageView) findViewById(R.id.imageIV);
        animationIV.setImageResource(R.drawable.prod_point_img);
        animationDrawable = (AnimationDrawable) animationIV.getDrawable();
        animationDrawable.start();

        animationIV.setX(x_px);
        animationIV.setY(y_px);

        locateBook = (ImageView) findViewById(R.id.book_locate);
        locateBook.setX(-200);
        locateBook.setY(-200);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(wifiReceiver != null) unregisterReceiver(wifiReceiver);
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 注销监听函数
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // 读取加速度传感器数值，values数组0,1,2分别对应x,y,z轴的加速度
        sensorVal = event.values;
    }

    public void showSensor() {
        mAccText = (TextView) findViewById(R.id.showAcc);
        StringBuilder showAcc = new StringBuilder();
        showAcc.append("X：");
        showAcc.append(sensorVal[0]);
        showAcc.append("\nY：");
        showAcc.append(sensorVal[1]);
        showAcc.append("\nZ：");
        showAcc.append(sensorVal[2]);
        mAccText.setText(showAcc.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
