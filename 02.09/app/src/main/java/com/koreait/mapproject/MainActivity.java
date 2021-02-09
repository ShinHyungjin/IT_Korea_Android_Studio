package com.koreait.mapproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    GoogleMap googleMap;
    BitmapDescriptor bitmapDescriptor;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message message) {
                Bundle bundle = message.getData();
                MarkerOptions options = (MarkerOptions)bundle.get("option");
                googleMap.addMarker(options);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(options.getPosition()));
            }
        };

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        Thread thread = new Thread() {
            public void run() {
                setMarker();
            }
        };
        thread.start();
    }

    public void setMarker() {
        LatLng pos = new LatLng(37.500689, 127.036759);
        MarkerOptions options = new MarkerOptions();
        Bitmap bitmap = null;
        options.position(pos);
        URL url = null;
        try {
            url = new URL("https://cdn2.iconfinder.com/data/icons/bitsies/128/FlagRed-128.png");
            bitmap = BitmapFactory.decodeStream(url.openStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        options.icon(bitmapDescriptor);

        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putParcelable("option", options);
        message.setData(bundle);
        handler.sendMessage(message);
    }
}