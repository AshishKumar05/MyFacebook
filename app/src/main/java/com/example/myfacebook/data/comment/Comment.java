package com.example.myfacebook.data.comment;

import com.example.myfacebook.data.mediapost.MediaAuthor;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("id")
    public String id;

    @SerializedName("message")
    public String message;

    @SerializedName("owner")
    public MediaAuthor author;

    @SerializedName("publishDate")
    public String publishDate;

}
