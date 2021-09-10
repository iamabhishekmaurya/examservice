package com.exam.common.enums;

import com.exam.common.utility.StringUtils;

public enum AppServiceCode {
    APP001("APP001", "Your request has been processed successfully"),
    APP002("APP002", "Oops! It seems your credentials did not match with our records"),
    //User Service code
    APP050("APP050", "Username already in used, try another username."),
    APP993("APP993", "Error in saving data."),
    APP994("APP994", "Invalid Request, missing parameters."),
    APP995("APP995", "Invalid Request"),
    APP996("APP996", "No record found"),
    APP997("APP997", "Invalid Request, Validation Error"),
    APP998("APP998", "Sorry! Security measures are not being followed"),
    APP999("APP999", "Unable to process your request, please try later"),
    ;
    String statusCode = null;
    String statusDesc = null;
    private AppServiceCode( String statusCode, String statusDesc )
    {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public static AppServiceCode getServiceByErrorKey( String errorKey )
    {
        for ( AppServiceCode serviceCode : AppServiceCode.values() )
        {
            if ( StringUtils.equals( serviceCode.getStatusCode(), errorKey ) )
            {
                return serviceCode;
            }
        }
        return AppServiceCode.APP997;
    }
}
