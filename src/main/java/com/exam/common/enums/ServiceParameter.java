package com.exam.common.enums;

import com.exam.common.utility.StringUtils;

public enum ServiceParameter {
    LIST("list"),
    VIEW("view"),
    CREATE("create"),
    UPDATE("update"),
    STATUS("status"),
    DELETE("delete"),
    MANAGE("manage"),
    VALIDATE("validate"),
    AUTHENTICATE("authenticate"),
    FORGOT_PASSWORD_NO_LINK("forgotPasswordNoLink"),
    FORGOT_PASSWORD("forgotPassword"),
    CHANGE_PASSWORD("changePassword"),
    RESET_PASSWORD("resetPassword"),
    SEARCH("search"),
    COUNT("count"),
    READ("read"),
    UNREAD("Unread"),
    LINK("link"),
    SUMMARY("summary"),
    DETAIL("detail"),
    REGISTER_DEVICE("registerDevice"),
    GET_USER_DEVICES("getUserDevices"),
    BY_USER("byUser"),
    SUBMIT("submit"),
    BULK_INSERT("bulkInsert"),
    DOWNLOAD("download"),
    REPORT("report");
    private String parameter = null;

    private ServiceParameter( String parameter )
    {
        this.parameter = parameter;
    }

    public String getParameter()
    {
        return parameter;
    }

    public static ServiceParameter getNPServiceParameter( String parameter )
    {
        {
            for ( ServiceParameter displayMsgEnum : ServiceParameter.values() )
                if ( StringUtils.equals( displayMsgEnum.getParameter(), parameter ) )
                {
                    return displayMsgEnum;
                }
        }
        return null;
    }
}
