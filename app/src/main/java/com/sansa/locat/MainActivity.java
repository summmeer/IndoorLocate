package com.sansa.locat;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private WifiScanReceiver wifiReceiver;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReceiver();
        handler.postDelayed(runnable, 2000);//flush 2s
    }

    /** Called at the beginning, scan-wifi signal every 2 seconds.*/
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            openWifi();
            handler.postDelayed(this, 2000);//2s
        }
    };

    /** Called when the user taps the Send button, go to a new page. */
    public void showDetail(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }

    /**Scan wifi ap-list use wifi-manager.*/
    public void openWifi() {
        WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!wifi.isWifiEnabled())wifi.setWifiEnabled(true); //Open wifi
        wifi.startScan();
        List<ScanResult> apList = wifi.getScanResults();
        DisplayMessageActivity.apList = wifiReceiver.ReadScan(apList).toString();
    }

    //Dynamic registration for wifi-scan-receiver
    private void initReceiver() {
        wifiReceiver = new WifiScanReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wifiReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(wifiReceiver != null)
        unregisterReceiver(wifiReceiver);
    }
}
