package com.test.networkchangetest;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;

/**
 * @author DavidZXY
 * @date 2019/12/26
 */
public class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {

    private static final String TAG = "NetworkCallbackImpl";

    public NetworkCallbackImpl() {
        super();
        LogUtils.i(TAG, "NetworkCallbackImpl: ");
    }

    @Override
    public void onAvailable(@NonNull Network network) {
        super.onAvailable(network);
        LogUtils.i(TAG, "onAvailable:", network.toString());
    }

    @Override
    public void onLosing(@NonNull Network network, int maxMsToLive) {
        super.onLosing(network, maxMsToLive);
        LogUtils.i(TAG, "onLosing:", network.toString(), maxMsToLive);
    }

    @Override
    public void onLost(@NonNull Network network) {
        super.onLost(network);
        LogUtils.i(TAG, "onLost:", network.toString());
    }

    @Override
    public void onUnavailable() {
        super.onUnavailable();
        LogUtils.i(TAG, "onUnavailable:");
    }

    @Override
    public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        LogUtils.i(TAG, "onCapabilitiesChanged:", network.toString(), networkCapabilities.toString());
    }

    @Override
    public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
        super.onLinkPropertiesChanged(network, linkProperties);
        LogUtils.i(TAG, "onLinkPropertiesChanged:");
    }

    @Override
    public void onBlockedStatusChanged(@NonNull Network network, boolean blocked) {
        super.onBlockedStatusChanged(network, blocked);
        LogUtils.i(TAG, "onBlockedStatusChanged:");
    }
}
