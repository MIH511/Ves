package com.delivery.ves.utils;

import java.io.UnsupportedEncodingException;

public class ConvertArabicNameToString {

    public static String getArabicString(String arabicText){
        byte[] charset = new byte[0];
        String result=null;
        try {
            charset = arabicText.getBytes("UTF-8");
             result= new String(charset, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
