package com.bridgelabz;

public class StateCensusAnalyserException extends Throwable {

    public ExceptionType type;
    private String message;

    enum ExceptionType {
        ENTERED_NULL, ENTERED_EMPTY, NO_SUCH_FILE, NO_SUCH_FIELD, NO_SUCH_METHOD, NO_SUCH_CLASS, OBJECT_CREATION_ISSUE, METHOD_INVOCATION_ISSUE, FIELD_SETTING_ISSUE
    }

    public StateCensusAnalyserException(ExceptionType type) {
        super(String.valueOf(type));
    }

    public StateCensusAnalyserException(String message) {
        super(message);
        this.message = message;
    }
}
