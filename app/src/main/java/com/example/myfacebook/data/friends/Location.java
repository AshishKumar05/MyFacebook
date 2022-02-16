package com.example.myfacebook.data.friends;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("street")
    public String street;

    @SerializedName("city")
    public String city;

    @SerializedName("state")
    public String state;

    @SerializedName("country")
    public String country;

}
