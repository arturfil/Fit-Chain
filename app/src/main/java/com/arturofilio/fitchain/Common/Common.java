package com.arturofilio.fitchain.Common;

import com.arturofilio.fitchain.models.User;

public class Common {

    public static User currentUser;

    public static String convertCodeToStatus(String status) {
        if(status.equals(0)) {
            return "Placed";
        } else if (status.equals(1)) {
            return "On it's Way";
        }
        return "Hello";
    }

}
