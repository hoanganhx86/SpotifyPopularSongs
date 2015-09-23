/**
 * UmbalaApp
 *
 * Created by Hoang Anh on 8/10/15.
 * Copyright (c) 2015 Umbala. All rights reserved.
 */
package com.anhnguyen.spotifypopularsongs.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.Button;
import android.widget.ImageView;

public class DrawableUtils {

    /**
     * Base on request new height we compute new width
     */
    public static void setImageViewDrawableSizeBaseOnHeight(ImageView imageView, int newHeight){
        Drawable drawable = imageView.getDrawable();
        if(drawable == null) return;
        int curWidth = drawable.getIntrinsicWidth();
        int curHeight = drawable.getIntrinsicHeight();

        float scale = 1f * newHeight / curHeight;
        int newWidth = (int) (scale * curWidth);

        drawable.setBounds(0, 0, newWidth, newHeight);
        imageView.setImageDrawable(drawable);
    }

    /**
     * Base on request new height we compute new width
     */
	public static void setCompoundDrawableSizeBaseOnHeight(Button button, int newHeight){
        Drawable[] drawables = button.getCompoundDrawables();
        for(Drawable drawable: drawables){
            if(drawable == null) continue;
            int curWidth = drawable.getIntrinsicWidth();
            int curHeight = drawable.getIntrinsicHeight();

            float scale = 1f * newHeight / curHeight;
            int newWidth = (int) (scale * curWidth);

            drawable.setBounds(0, 0, newWidth, newHeight);
        }
        button.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
	}

    /**
     * Base on request new width we compute new height
     */
    public static void setCompoundDrawableSizeBaseOnWidth(Button button, int newWidth){
        Drawable[] drawables = button.getCompoundDrawables();
        for(Drawable drawable: drawables){
            if(drawable == null) continue;
            int curWidth = drawable.getIntrinsicWidth();
            int curHeight = drawable.getIntrinsicHeight();

            float scale = 1f * newWidth / curWidth;
            int newHeight = (int) (scale * curHeight);

            drawable.setBounds(0, 0, newWidth, newHeight);
        }
        button.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }


    public static void setCompoundDrawableSize(Button compoundButton, int width, int height){
		Drawable[] drawables = compoundButton.getCompoundDrawables();
		for(Drawable drawable: drawables){
			if(drawable == null) continue;
			drawable.setBounds(0, 0, width, height);
		}
		
		compoundButton.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
	}


    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static void tintButtonDrawable(Button button, int tintColor){
        Drawable[] drawables = button.getCompoundDrawables();
        for(int i = 0; i < drawables.length; i++){
            if(drawables[i] == null) continue;
            drawables[i] = tintingDrawable(drawables[i], tintColor);
        }
        button.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public static Drawable tintingDrawable(Drawable drawable, int tintColor){
        // Wrap the drawable so that future tinting calls work
        // on pre-v21 devices. Always use the returned drawable.
        drawable = DrawableCompat.wrap(drawable);

        // We can now set a tint
        DrawableCompat.setTint(drawable, tintColor);
//        // ...and a different tint mode
//        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_OVER);

        return drawable;
    }

}