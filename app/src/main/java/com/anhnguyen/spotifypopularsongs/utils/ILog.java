package com.anhnguyen.spotifypopularsongs.utils;

import com.anhnguyen.spotifypopularsongs.Configs;

import android.util.Log;

public class ILog {

    public static void d(String tag, String msg) {
        if (Configs.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (Configs.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (Configs.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (Configs.DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (Configs.DEBUG) {
            Log.w(tag, msg);
        }
    }


    public static void d(String tag, String msg, Throwable tr) {
        if (Configs.DEBUG) {
            Log.d(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (Configs.DEBUG) {
            Log.e(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (Configs.DEBUG) {
            Log.i(tag, msg, tr);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (Configs.DEBUG) {
            Log.v(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (Configs.DEBUG) {
            Log.w(tag, msg, tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (Configs.DEBUG) {
            Log.w(tag, tr);
        }
    }

}
