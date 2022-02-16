package com.example.myfacebook.ui.home.comment;

import androidx.lifecycle.MutableLiveData;

import com.example.myfacebook.data.APIManager;
import com.example.myfacebook.data.APIResponse;
import com.example.myfacebook.data.comment.CommentResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRepository {

    public APIManager apiManager;

    public CommentRepository(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    public MutableLiveData<APIResponse<CommentResponse>> get_comment(String token,String postId){

        final APIResponse<CommentResponse> apiResponse = new APIResponse<>();
        final MutableLiveData<APIResponse<CommentResponse>> mutableLiveData = new MutableLiveData<>();

        Call<CommentResponse> commentList = apiManager.getCommentList(token,postId);

        commentList.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if(!response.isSuccessful()) {
                    try {
                        apiResponse.setError(true);
                        apiResponse.setErrorMessage(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                        apiResponse.setResponse(response.body());
                }
                mutableLiveData.setValue(apiResponse);
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                apiResponse.setError(true);
                apiResponse.setErrorMessage(t.getMessage());
                mutableLiveData.setValue(apiResponse);
            }
        });
        return mutableLiveData;
    }
}
