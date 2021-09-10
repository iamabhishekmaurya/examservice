package com.exam.common.utility;

import com.exam.common.enums.AppServiceCode;
import com.exam.dtos.response.ResponseDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isEmpty(String user){
        if(user.equals("")){
            return true;
        }
        return  false;
    }

    public static boolean isValidObj(Object obj){
        if(obj != null){
            return true;
        }
        return  false;
    }

    public static boolean isPhoneNumber(String phone) {
        Pattern pattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$");
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean equals(String str1, String str2){
        if(str1.equals(str2))
            return true;
        return false;
    }

    public static ResponseDto setResponseCodeAndMessage(ResponseDto response, AppServiceCode serviceCode) {
        response.setStatus(serviceCode.getStatusCode());
        response.setMessage(serviceCode.getStatusDesc());
        return response;
    }
}
