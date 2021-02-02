package com.koreait.graphicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.koreait.graphicapp.gallery.GalleryView;

public class GalleryActivity extends AppCompatActivity {
    GalleryView galleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        galleryView = findViewById(R.id.galleryView);
    }

    public void next(View view) {
        galleryView.loadImage(galleryView.imgUrl[galleryView.index++]);
        if (galleryView.index == galleryView.imgUrl.length) {
            galleryView.index = 0;
        }
        galleryView.invalidate();
    }

    public void prev(View view) {
        galleryView.loadImage(galleryView.imgUrl[galleryView.index--]);
        if (galleryView.index == 0) {
            galleryView.index = galleryView.imgUrl.length - 1;
        }
        galleryView.invalidate();
    }

    public void stop(View view) {

    }

    public void auto(View view) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    galleryView.loadImage(galleryView.imgUrl[galleryView.index++]);
                    if (galleryView.index == galleryView.imgUrl.length) {
                        galleryView.index = 0;
                    }
                    galleryView.invalidate();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}