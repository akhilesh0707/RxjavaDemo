package com.aqube.rxjava.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUtil {

    public static List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "Akhilesh"));
        userList.add(new User(2, "Dheeraj"));
        userList.add(new User(3, "Vinay"));
        userList.add(new User(4, "Jibin"));
        userList.add(new User(5, "Deepesh"));
        userList.add(new User(6, "Umesh"));
        return userList;
    }

    public static User getUser() {
        return new User(7,"Akhliesh");
    }

    public static Map<Integer, String> getUserAddress() {
        Map<Integer, String> userAddressMap = new HashMap<>();
        userAddressMap.put(1, "123 Abc street, A block");
        userAddressMap.put(2, "321 Cde street, D block");
        userAddressMap.put(3, "213 Xyz street, E block");
        userAddressMap.put(4, "312 Vxy street, J block");
        userAddressMap.put(5, "400 Dfg street, D-1 block");
        userAddressMap.put(6, "612 Upx street, U block");
        return userAddressMap;
    }

}
