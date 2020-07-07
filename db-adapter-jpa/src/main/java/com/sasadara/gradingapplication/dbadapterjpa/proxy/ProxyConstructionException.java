package com.sasadara.gradingapplication.dbadapterjpa.proxy;

class ProxyConstructionException extends RuntimeException {
    ProxyConstructionException() {
    }

    ProxyConstructionException(String message) {
        super(message);
    }

    ProxyConstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    ProxyConstructionException(Throwable cause) {
        super(cause);
    }

    ProxyConstructionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
