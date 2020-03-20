package com.test.networkchangetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.widget.Toast;

import com.blankj.utilcode.util.NetworkUtils;

/**
 * @author DavidZXY
 */
public class MainActivity extends AppCompatActivity {

    NetworkUtils.OnNetworkStatusChangedListener mNetworkStatusChangedListener = new NetworkUtils.OnNetworkStatusChangedListener() {
        @Override
        public void onDisconnected() {
            Toast.makeText(MainActivity.this, "网络连接中断", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onConnected(NetworkUtils.NetworkType networkType) {
            Toast.makeText(MainActivity.this, networkType.name(), Toast.LENGTH_SHORT).show();
        }
    };
//
//    ConnectivityManager mConnectivityManager;
//    ConnectivityManager.NetworkCallback mNetworkCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkUtils.registerNetworkStatusChangedListener(mNetworkStatusChangedListener);
//        mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        mNetworkCallback = new NetworkCallbackImpl();
//        NetworkRequest.Builder networkRequestBuilder = new NetworkRequest.Builder();
//        mConnectivityManager.registerNetworkCallback(networkRequestBuilder.build(), mNetworkCallback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkUtils.unregisterNetworkStatusChangedListener(mNetworkStatusChangedListener);
//        mConnectivityManager.unregisterNetworkCallback(mNetworkCallback);
    }
}
