package com.example.myfacebook.data.comment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentResponse {

    @SerializedName("data")
    public List<Comment> commentList;
}
