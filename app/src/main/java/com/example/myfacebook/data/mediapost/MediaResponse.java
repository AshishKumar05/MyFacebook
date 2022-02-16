package com.example.myfacebook.data.mediapost;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MediaResponse {

    @SerializedName("data")
    public List<Mediapost> MediapostList;

}
