package com.example.a1220_rk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WiFiUtils {
    /**
     * WiFi帮助类
     */
        // 定义WifiManager对象
        private WifiManager mWifiManager;
        // 定义WifiInfo对象
        private WifiInfo mWifiInfo;
        // 扫描出的网络连接列表

        // 构造器
        public WiFiUtils(Context context) {
            // 取得WifiManager对象
            mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            // 取得WifiInfo对象
            mWifiInfo = mWifiManager.getConnectionInfo();
        }

        // 打开WIFI
        public void openWifi() {
            if (!mWifiManager.isWifiEnabled()) {
                mWifiManager.setWifiEnabled(true);
            }
        }

        // 关闭WIFI
    public void closeWifi() {
        if (mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(false);
        }
    }


    // 获取网络状态
    public boolean getNet(Context context) {
            //初始化网络状态管理者
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
             //网络类型
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
          return networkInfo.isConnected();
    }

        // 检查当前WIFI状态
    /*
    * WifiManager.WIFI_STATE_DISABLING : WIFI网卡正在关闭（0）
WifiManager.WIFI_STATE_DISABLED : WIFI网卡不可用（1）
WifiManager.WIFI_STATE_ENABLING : WIFI网正在打开（2） （WIFI启动需要一段时间）
WifiManager.WIFI_STATE_ENABLED : WIFI网卡可用（3）
WifiManager.WIFI_STATE_UNKNOWN  : 未知网卡状态

作者：Rair
链接：https://www.jianshu.com/p/c415691b282c
來源：简书
简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
    *
    * */
        public int checkState() {
            return mWifiManager.getWifiState();
        }




}
