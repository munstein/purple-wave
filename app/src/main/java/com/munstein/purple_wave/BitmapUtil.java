package com.munstein.purple_wave;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.media.ExifInterface;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by @Munstein on 28/12/2017. --21:18
 */

public class BitmapUtil {

    public static int getImageRotationWithExifInterfaceSupport(Context context, Uri uri) {

        int orientation;
        InputStream in;
        try {
            in = context.getContentResolver().openInputStream(uri);
            ExifInterface exifInterface = new ExifInterface(in);
            orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    orientation = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    orientation = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    orientation = 270;
                    break;
            }
        } catch (IOException x) {
            return 0;
        }
        return orientation;
    }

    public static Bitmap getImageFromUri(Context context, Uri uri) {

        try {
            final InputStream imageStream = context.getContentResolver().openInputStream(uri);
            Bitmap img = BitmapFactory.decodeStream(imageStream);
            return img;
        } catch (FileNotFoundException e) {
            return null;
        }

    }


}
