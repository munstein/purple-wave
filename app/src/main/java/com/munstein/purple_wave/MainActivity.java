package com.munstein.purple_wave;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_GALLERY = 2018;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgView = findViewById(R.id.main_imgview);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromGallery(REQUEST_CODE_GALLERY);
            }
        });
    }

    private void getImageFromGallery(int resultCode) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, resultCode);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && reqCode == REQUEST_CODE_GALLERY) {
            Uri imgUri = data.getData();
            Bitmap bitmap = BitmapUtil.getImageFromUri(this, imgUri);
            if (bitmap != null) {
                imgView.setImageBitmap(PurpleCore.Purple(bitmap, this));
                imgView.setRotation(BitmapUtil
                        .getImageRotationWithExifInterfaceSupport(this, imgUri));
            }
        }
    }
}
