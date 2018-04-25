package com.sansa.locat;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.sansa.locat.MESSAGE";
    private WifiScanReceiver wifiReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReceiver();
    }
    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void openWifi(View view) {
        // Do something in response to button
        WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!wifi.isWifiEnabled())wifi.setWifiEnabled(true);
        wifi.startScan();
        List<ScanResult> apList = wifi.getScanResults();
        TextView textView = findViewById(R.id.textView);
        textView.setText(ReadScan(apList).toString());
    }

    public void closeWifi(View view) {
        // Do something in response to button
        WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(wifi.isWifiEnabled())wifi.setWifiEnabled(false);
    }

    private StringBuilder ReadScan(List<ScanResult> apList) {
        StringBuilder StringList = new StringBuilder();
        for (int i = 0; i < apList.size(); i++) {
            StringList.append("Index_").append(Integer.valueOf(i + 1).toString()).append(":");
            StringList.append((apList.get(i).SSID)).append(" // ").append((apList.get(i).BSSID)).append(" // ").append(apList.get(i).level);
            StringList.append("\n");
        }
        return StringList;
    }

    //Dynamic registration
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
