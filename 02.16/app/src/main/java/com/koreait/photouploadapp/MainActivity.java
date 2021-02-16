package com.koreait.photouploadapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    String TAG = this.getClass().getName();
    public static int CAMERA_REQUEST = 1;
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
    }

    public void take(View view) {
        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.CAMERA
        }, CAMERA_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_REQUEST) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callCamera();
            }else {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("권한안내").setMessage("서비스 이용을 위해서는 카메라 권한을 승인해야 합니다").create().show();
            }
        }
    }
    public void callCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap)extras.get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    public void save(View view) {
        File folder = new File(this.getExternalFilesDir(null), "pic");
        folder.mkdir();

        String filename = System.currentTimeMillis() + ".jpg";
        File file = new File(folder, filename);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

            Log.d(TAG, "파일 존재여부 : " + file.exists());

            Toast.makeText(this,"이미지가 저장되었습니다.", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "저장경로 : " + this.getExternalFilesDir(null));
    }
    public void send(View view) {

    }

}