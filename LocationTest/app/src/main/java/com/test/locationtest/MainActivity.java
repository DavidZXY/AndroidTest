package com.test.locationtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.test.locationtest.utils.LocationUtils;

import java.util.ArrayList;

/**
 * @author DavidZXY
 */
public class MainActivity extends AppCompatActivity {

    private Button mLocationButton;
    private TextView mLocationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initEvent();
    }

    private void initData() {
    }

    private void initView() {
        mLocationButton = findViewById(R.id.location_button);
        mLocationTextView = findViewById(R.id.location_text);
    }

    private void initEvent() {

        mLocationButton.setOnClickListener(v -> {
            registerLocation();
        });
    }

    private void registerLocation() {
        // 116.426021, 39.959204
//        if (LocationUtils.isGpsEnabled() && LocationUtils.isLocationEnabled()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
            LocationUtils.register(0, 0, new LocationUtils.OnLocationChangeListener() {
                @Override
                public void getLastKnownLocation(Location location) {
                }

                @Override
                public void onLocationChanged(Location location) {
                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();
                    Address address = LocationUtils.getAddress(latitude, longitude);
                    int n = address.getMaxAddressLineIndex();
                    if (n == -1) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                        }
                        LocationUtils.unregister();
                        return;
                    }
                    ArrayList<String> arrayList  = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        arrayList.add(address.getAddressLine(i));
                    }
                    AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                            .setItems((String[]) arrayList.toArray(new String[arrayList.size()]), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mLocationTextView.setText(arrayList.get(which));
                                }
                            })
                            .create();
                    dialog.show();
//                    String country = LocationUtils.getCountryName(latitude, longitude);
//                    String locationCity = LocationUtils.getLocality(latitude, longitude);
//                    String street = LocationUtils.getStreet(latitude, longitude);
                    LogUtils.i("MainActivity", address.toString());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                    }
                    LocationUtils.unregister();
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }
            });

//        }
//        else {
//            Toast.makeText(MainActivity.this, "请打开GPS", Toast.LENGTH_SHORT).show();
//            LocationUtils.openGpsSettings();
//        }
    }
}
