package com.test.automation.mysql;

class SQLRuntimeException extends RuntimeException {

    SQLRuntimeException() {
        super();
    }

    SQLRuntimeException(String message) {
        super(message);
    }

    SQLRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    SQLRuntimeException(Throwable cause) {
        super(cause);
    }

}
