package com.anhnguyen.spotifypopularsongs.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Class containing some static utility methods.
 */
public class Utils {

    private static final String TAG = "UBLUtils";
    static Boolean sIsSmallestScreenWidthLessThan600dp;
    static Boolean sIsSmallestScreenWidthMoreThan600dp;
    static Boolean sIsSmallestScreenWidthMoreThan720dp;
    private static int sStatusBarHeight = -1;
    
    @TargetApi(11)
    public static void enableStrictMode() {
        if (Utils.hasGingerbread()) {
            StrictMode.ThreadPolicy.Builder threadPolicyBuilder =
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .penaltyLog();
            StrictMode.VmPolicy.Builder vmPolicyBuilder =
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog();

            if (Utils.hasHoneycomb()) {
                /*threadPolicyBuilder.penaltyFlashScreen();
                vmPolicyBuilder
                        .setClassInstanceLimit(ImageGridActivity.class, 1)
                        .setClassInstanceLimit(ImageDetailActivity.class, 1);*/
            }
            StrictMode.setThreadPolicy(threadPolicyBuilder.build());
            StrictMode.setVmPolicy(vmPolicyBuilder.build());
        }
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean isHoneycombTablet(Context context) {
        return hasHoneycomb() && isTablet(context);
    }

    /**
     * Get the size in bytes of a bitmap.
     * @param bitmap
     * @return size in bytes
     */
    @TargetApi(12)
    public static int getBitmapSize(Bitmap bitmap) {
        if (Utils.hasHoneycombMR1()) {
            return bitmap.getByteCount();
        }
        // Pre HC-MR1
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public static boolean hasFroyo() {
        // Can use static final constants like FROYO, declared in later versions
        // of the OS since they are inlined at compile time. This is guaranteed behavior.
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static int PixelToSp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
	    return (int) (px/scaledDensity);
	}
	
	public static int spToPixel(Context context, float sp) {
	    float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
	    return (int) (sp*scaledDensity);
	}

	public static int convertDpToPixel(Context context, float dp){
		float px = dp * context.getResources().getDisplayMetrics().densityDpi / 160.0f;
		return (int)px;
	}

    public static int convertPixelsToDp(Context context, float px) {
        float dp = px * 160.0f / context.getResources().getDisplayMetrics().densityDpi;
	    return (int)dp;
	}

    public static int pixelsFromPts(Context context, float pts) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT,
                pts,
                context.getResources().getDisplayMetrics());
    }

    public static int pixelsFromInches(Context context, float inches) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_IN,
        		inches,
                context.getResources().getDisplayMetrics());
    }

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void setBackgroundSafe(View v, Drawable d){
		if(hasJellyBean()){
			v.setBackground(d);
		}else{
			v.setBackgroundDrawable(d);
		}
	}

    public static float getDimensionInDpUnit(Context context, int dimension) {
        return context.getResources().getDimension(dimension) / context.getResources().getDisplayMetrics().density;
	}

    public static float getSmallestScreenWidthInDpUnit(Context context){
    	Configuration configuration = context.getResources().getConfiguration();
    	int screenWidthDp = configuration.screenWidthDp;
        int smallestScreenWidthDp = configuration.smallestScreenWidthDp;
        ILog.d(TAG, "UBLUtils screenWidthDp = " + screenWidthDp + " smallestScreenWidthDp" + smallestScreenWidthDp);
        return smallestScreenWidthDp;
    }

    public static boolean isSmallestScreenWidthLessThan600dp(Context context){
        if(sIsSmallestScreenWidthLessThan600dp == null) {
            sIsSmallestScreenWidthLessThan600dp = getSmallestScreenWidthInDpUnit(context) < 600f;
        }
        return sIsSmallestScreenWidthLessThan600dp;
    }

    public static boolean isSmallestScreenWidthAtLeast600dp(Context context){
        if(sIsSmallestScreenWidthMoreThan600dp == null) {
            sIsSmallestScreenWidthMoreThan600dp = getSmallestScreenWidthInDpUnit(context) >= 600f;
        }
        return sIsSmallestScreenWidthMoreThan600dp;
    }

    public static boolean isSmallestScreenWidthAtLeast720dp(Context context){
        if(sIsSmallestScreenWidthMoreThan720dp == null) {
            sIsSmallestScreenWidthMoreThan720dp = getSmallestScreenWidthInDpUnit(context) >= 720f;
        }
        return sIsSmallestScreenWidthMoreThan720dp;
    }

    public static int getStatusBarHeight(Context context){
    	if(sStatusBarHeight == -1){
    		sStatusBarHeight = 0;
	        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
	        if (resourceId > 0) {
	        	sStatusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
	        }
    	}
        return sStatusBarHeight;
    }


    /**
     * Gets the size of the display, in pixels.
     * <p>
     * Note that this value should <em>not</em> be used for computing layouts,
     * since a device will typically have screen decoration (such as a status bar)
     * along the edges of the display that reduce the amount of application
     * space available from the size returned here.  Layouts should instead use
     * the window size.
     * </p><p>
     * The size is adjusted based on the current rotation of the display.
     * </p><p>
     * The size returned by this method does not necessarily represent the
     * actual raw size (native resolution) of the display.  The returned size may
     * be adjusted to exclude certain system decoration elements that are always visible.
     * It may also be scaled to provide compatibility with older applications that
     * were originally designed for smaller displays.
     * </p>
     */
    public static Point getWindowSize(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size;
    }


    /**
     * List all app permissions
     */
    public static void printOutPermissions(Context context){
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            if (info.requestedPermissions != null) {
                for (String p : info.requestedPermissions) {
                    ILog.d(TAG, "Permission :" + p);
                }
            }

            if (info.permissions != null) {
                for (PermissionInfo permission : info.permissions) {
                    ILog.d(TAG, "2 Permission :" + permission.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String dumpBundle(Bundle b) {
        if (b == null) return "null";
        StringBuilder sb = new StringBuilder("Bundle{");
        boolean first = true;
        for (String key : b.keySet()) {
            if (!first) sb.append(" ");
            first = false;
            sb.append(key+"=(");
            sb.append(b.get(key));
        }
        sb.append("}");
        return sb.toString();
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void hideSoftKeyboard(Activity activity) {
        final View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        final IBinder windowToken = currentFocus.getWindowToken();
        imm.hideSoftInputFromWindow(windowToken, 0);
    }


    public static boolean removeDirectory(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = removeDirectory(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static void captureView(View view, File outputFile) {
        //Create a Bitmap with the same dimensions
        Bitmap image = Bitmap.createBitmap(
                view.getWidth(),
                view.getHeight(),
                Bitmap.Config.RGB_565);
        //Draw the view inside the Bitmap
        view.draw(new Canvas(image));

        //Store to sdcard
        try {
            if (outputFile.exists()) {
                outputFile.delete();
            }
            FileOutputStream out = new FileOutputStream(outputFile);

            image.compress(Bitmap.CompressFormat.PNG, 90, out); //Output
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
