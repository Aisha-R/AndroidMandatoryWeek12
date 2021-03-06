package com.aisharooble.imagedemowithrefactor.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;

import com.aisharooble.imagedemowithrefactor.MainActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageController {

    private MainActivity mainActivity;

    public ImageController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void handleImageReturn(int requestCode, @Nullable Intent intent) {
        if(requestCode == 0){ // it is the photoroll
            // 2. get the url for the image
            Uri uri = intent.getData();
            try {
                // 3. Create an inputstream to read the file
                InputStream is = mainActivity.getContentResolver().openInputStream(uri);  // the other is ContentProvider
                // 4. Make Bitmap from stream
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                //HW: reduce size of image
                Bitmap resized = Bitmap.createScaledBitmap(bitmap, 400, 300, true);
                // 5. Set bitmap to imageView
                mainActivity.imageView.setImageBitmap(resized);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == 1){ // it's the camera
            Bitmap bitmap = (Bitmap) intent.getExtras().get("data");  // here, the data itself was provided
            // with the intent
            // 2. assign bitmap to imageView
            mainActivity.imageView.setImageBitmap(bitmap);
        }
    }
}
