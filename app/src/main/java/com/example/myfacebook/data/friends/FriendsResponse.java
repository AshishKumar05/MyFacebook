package com.example.myfacebook.data.friends;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FriendsResponse {

    @SerializedName("data")
    public List<User> userList;
}
