package com.sansa.locat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by sansa on 2018/4/13.
 */

public class WifiScanReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context c, Intent intent) {
    }

    /**Transform ap-list to string*/
    public StringBuilder ReadScan(List<ScanResult> apList) {
        StringBuilder StringList = new StringBuilder();
        for (int i = 0; i < apList.size(); i++) {
            StringList.append("Index_").append(Integer.valueOf(i + 1).toString()).append(":");
            StringList.append((apList.get(i).SSID)).append(" // ").append((apList.get(i).BSSID)).append(" // ").append(apList.get(i).level);
            StringList.append("\n");
        }
        return StringList;
    }
}
