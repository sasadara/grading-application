package com.sasadara.gradingapplication.entities.question;

public enum Results {
    RIGHT(0), WRONG(1), PARTIAL(2);

    private int value;

    Results(int value) {
        this.value = value;
    }

    public static Results from(String value) {

        // Checking for null first
        // else NullPointerException will get thrown in the if-else if blocks below
        if (value == null) {
            return null;
        }

        if (value.equalsIgnoreCase("RIGHT")) {
            return RIGHT;

        } else if (value.equalsIgnoreCase("WRONG")) {
            return WRONG;

        } else if (value.equalsIgnoreCase("PARTIAL")) {
            return PARTIAL;

        }
        return null;

    }

    public int getValue() {
        return value;
    }
}
