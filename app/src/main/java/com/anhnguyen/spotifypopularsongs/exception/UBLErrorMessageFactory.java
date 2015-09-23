package com.anhnguyen.spotifypopularsongs.exception;

import com.anhnguyen.data.exception.NetworkConnectionException;

import android.content.Context;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class UBLErrorMessageFactory {

    private UBLErrorMessageFactory() {
        //empty
    }

    /**
     * Creates a String representing an error message.
     *
     * @param context Context needed to retrieve string resources.
     * @param exception An exception used as a condition to retrieve the correct error message.
     * @return {@link String} an error message.
     */
    public static String create(Context context, Exception exception) {
        //Exception messages
        String message = "There was an application error: " + exception.getMessage();

        if (exception instanceof NetworkConnectionException) {
            message = "There is no internet connection";
        }

        return message;
    }
}
