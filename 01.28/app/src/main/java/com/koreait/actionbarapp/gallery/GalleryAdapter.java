package com.koreait.actionbarapp.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koreait.actionbarapp.MainActivity;
import com.koreait.actionbarapp.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends BaseAdapter {
    String TAG = this.getClass().getName();
    LayoutInflater layoutInflater = null;
    List<Gallery> galleryList = new ArrayList<>();
    MainActivity mainActivity;

    public GalleryAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        layoutInflater = mainActivity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return galleryList.size();
    }

    @Override
    public Object getItem(int position) {
        return galleryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        Gallery gallery = galleryList.get(position);
        return gallery.getGallery_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if(convertView == null) {
            view = layoutInflater.inflate(R.layout.item_gallery, parent, false);
        }else {
            view = convertView;
        }
        Gallery gallery = galleryList.get(position);

        ImageView img = view.findViewById(R.id.img);
        TextView t_title = view.findViewById(R.id.t_title);

        img.setImageBitmap(galleryList.get(position).getBitmap());
        t_title.setText(gallery.getTitle());

        view.setOnClickListener(e-> {
            Toast.makeText(mainActivity, "클릭한 이미지는 " + gallery.getTitle(), Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
