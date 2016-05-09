package com.bvan.chatee.common;

/**
 * Utilities class contains helpful validation methods.
 *
 * @author bvanchuhov
 */
public final class Validator {

    private Validator() {
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
