package com.example.myfacebook.data;

import com.example.myfacebook.data.comment.CommentResponse;
import com.example.myfacebook.data.friends.FriendsResponse;
import com.example.myfacebook.data.mediapost.MediaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIManager {

    //get list of post
    @GET("/data/v1/post/")
    Call<MediaResponse> getMediaList(@Header("app-id") String token);

    //get Comment list for a post
    @GET("/data/v1/post/{postid}/comment")
    Call<CommentResponse> getCommentList(@Header("app-id") String token,
                                         @Path("postid") String postid);

    @GET("/data/v1/user/")
    Call<FriendsResponse> getFriendsList(@Header("app-id") String token);

}
