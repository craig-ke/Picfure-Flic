package com.craigke.picflic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;

public class ActivityFolder3 extends AppCompatActivity {

//    private int mPosition;
//    private String mSource;


        private static final int REQUEST_IMAGE_CAPTURE = 101;
        private ImageView mImageView;
//    private static final int CAMERA_PERMISSION_REQUEST_CODE = 11;
//    private String currentPhotoPath;
//    private static final String TAG = "image creation value";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_folder3);
            mImageView=findViewById(R.id.imageView2);
        }
        public void takePicture (View view){
            Intent imageTakeIntent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
            if(imageTakeIntent.resolveActivity(getPackageManager())!=null)
            {
                startActivityForResult(imageTakeIntent,REQUEST_IMAGE_CAPTURE);
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_IMAGE_CAPTURE && requestCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mImageView.setImageBitmap(imageBitmap);
            }

        }
}