package com.aqube.rxjava.util;

import java.util.ArrayList;
import java.util.List;

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
}
