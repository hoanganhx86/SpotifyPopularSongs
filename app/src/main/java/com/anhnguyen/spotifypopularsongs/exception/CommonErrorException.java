package com.anhnguyen.spotifypopularsongs.exception;

public class CommonErrorException extends RuntimeException {

    public CommonErrorException() {
        super();
    }

    public CommonErrorException(final String message) {
        super(message);
    }

    public CommonErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CommonErrorException(final Throwable cause) {
        super(cause);
    }
}
