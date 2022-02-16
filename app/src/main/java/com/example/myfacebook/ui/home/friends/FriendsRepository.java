package com.example.myfacebook.ui.home.friends;

import androidx.lifecycle.MutableLiveData;

import com.example.myfacebook.data.APIManager;
import com.example.myfacebook.data.APIResponse;
import com.example.myfacebook.data.friends.FriendsResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsRepository {

    private APIManager apiManager;

    public FriendsRepository(APIManager apiManager) {
        this.apiManager = apiManager;
    }


    MutableLiveData<APIResponse<FriendsResponse>> getFriends(String token){

        final MutableLiveData<APIResponse<FriendsResponse>> mutableLiveData = new MutableLiveData<>();
        final APIResponse<FriendsResponse> apiResponse = new APIResponse<>();

        Call<FriendsResponse> getFriends=apiManager.getFriendsList(token);
        getFriends.enqueue(new Callback<FriendsResponse>() {
            @Override
            public void onResponse(Call<FriendsResponse> call, Response<FriendsResponse> response) {

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
            public void onFailure(Call<FriendsResponse> call, Throwable t) {
                   apiResponse.setError(true);
                   apiResponse.setErrorMessage(t.getMessage());
                   mutableLiveData.setValue(apiResponse);
            }
        });
        return mutableLiveData;
    }




}
