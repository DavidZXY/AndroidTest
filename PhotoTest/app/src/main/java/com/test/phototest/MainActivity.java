package com.test.phototest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.PathUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button mTakePhotoButton;
    private ImageView mPhotoImageView;

    final static int CAPTURE_PHOTO_REQUEST_CODE = 0;
    File photoFile = new File(PathUtils.getExternalAppCachePath(), "agent.jpg");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initView() {
        mTakePhotoButton = findViewById(R.id.take_photo_button);
        mPhotoImageView = findViewById(R.id.photo_image_view);
    }

    private void initEvent() {
        mTakePhotoButton.setOnClickListener(v -> {
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//            Uri fileUri = Uri.fromFile(photoFile);
            Uri fileUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", photoFile);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            captureIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            captureIntent.putExtra("return-data", false);
            startActivityForResult(captureIntent, CAPTURE_PHOTO_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_PHOTO_REQUEST_CODE) {
            mPhotoImageView.setImageBitmap(BitmapFactory.decodeFile(photoFile.getAbsolutePath()));
        }
    }
}
