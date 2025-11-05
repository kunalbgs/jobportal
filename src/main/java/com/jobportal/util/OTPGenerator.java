package com.jobportal.util;

import java.util.Random;

public class OTPGenerator {
    public static String generateOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}