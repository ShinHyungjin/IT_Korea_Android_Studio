package com.koreait.actionbarapp.gallery;

import android.graphics.Bitmap;

public class Gallery {
    private int gallery_id;
    private String title;
    private String filename;
    //ImageView는 String으로 처리 불가.. 부족하니 더 추가 할 예정
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getGallery_id() {
        return gallery_id;
    }

    public void setGallery_id(int gallery_id) {
        this.gallery_id = gallery_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
