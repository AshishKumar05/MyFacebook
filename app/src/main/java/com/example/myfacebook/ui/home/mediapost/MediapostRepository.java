package com.example.myfacebook.ui.home.mediapost;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myfacebook.data.APIManager;
import com.example.myfacebook.data.APIResponse;
import com.example.myfacebook.data.mediapost.MediaResponse;
import com.example.myfacebook.data.mediapost.Mediapost;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediapostRepository {

    private APIManager apiManager;

    public MediapostRepository(APIManager apiManager) {
        this.apiManager = apiManager;
    }

    public MutableLiveData<APIResponse<MediaResponse>> get_mediapost(String token) {
        final APIResponse<MediaResponse> apiResponse = new APIResponse<>();
        final MutableLiveData<APIResponse<MediaResponse>> mutableLiveData = new MutableLiveData<>();

        Call<MediaResponse> get_mediapost = apiManager.getMediaList(token);
        get_mediapost.enqueue(new Callback<MediaResponse>() {
            @Override
            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {
                Log.d("MediapostRepository", "onResponse: ");
               if(!response.isSuccessful()) {
                    Log.d("MediapostRepository", "onResponseFail: ");
                    try {
                        apiResponse.setError(true);
                        apiResponse.setErrorMessage(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Log.d("MediapostRepository", "onResponseSuccess: Ashish");
                    apiResponse.setResponse(response.body());
                }
                mutableLiveData.setValue(apiResponse);
            }

            @Override
            public void onFailure(Call<MediaResponse> call, Throwable t) {
                Log.d("MediapostRepository", "onFailures: ");
               apiResponse.setError(true);
               apiResponse.setErrorMessage(t.getMessage());
               mutableLiveData.setValue(apiResponse);
            }
        });
        return mutableLiveData;
    }
}
