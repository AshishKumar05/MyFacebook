package com.example.myfacebook.data.friends;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("firstName")
    public String firstName;

    @SerializedName("lastName")
    public String lastName;

    @SerializedName("gender")
    public String gender;

    @SerializedName("email")
    public String email;

    @SerializedName("dateOfBirth")
    public String dateOfBirth;

    @SerializedName("registerDate")
    public String registerDate;

    @SerializedName("phone")
    public String phone;

    @SerializedName("picture")
    public String url;

    @SerializedName("location")
    public Location location;

}
