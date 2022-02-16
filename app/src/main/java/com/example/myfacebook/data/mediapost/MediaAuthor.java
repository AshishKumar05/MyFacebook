package com.example.myfacebook.data.mediapost;

import com.google.gson.annotations.SerializedName;

public class MediaAuthor {

    @SerializedName("id")
    public String authorId;

    @SerializedName("email")
    public String email;

    @SerializedName("title")
    public String title;

    @SerializedName("firstName")
    public String firstName;

    @SerializedName("lastName")
    public String lastName;

    @SerializedName("picture")
    public String pic_url;
}
