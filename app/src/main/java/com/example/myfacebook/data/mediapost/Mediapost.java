package com.example.myfacebook.data.mediapost;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mediapost {

   @SerializedName("id")
    public String post_id;

   @SerializedName("image")
    public String picture;

   @SerializedName("owner")
   public MediaAuthor author;

   @SerializedName("publishDate")
    public String date;

   @SerializedName("text")
    public String description;

   @SerializedName("tags")
    public List<String> tags;

   @SerializedName("likes")
    public String like;

}
