package com.example.myfacebook.ui.home.friends;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myfacebook.MyFacebookApplication;
import com.example.myfacebook.data.APIResponse;
import com.example.myfacebook.data.friends.FriendsResponse;
import com.example.myfacebook.utils.Constants;

public class FriendsViewModel extends AndroidViewModel {

    public FriendsRepository friendsRepository;
    public MyFacebookApplication app;


    public FriendsViewModel(@NonNull Application application) {
        super(application);
        app = (MyFacebookApplication) application;
        friendsRepository = new FriendsRepository(app.getAPIManager());
    }

    public String getToken() {
        return Constants.token;
    }

    public MutableLiveData<APIResponse<FriendsResponse>> getFriends() {
        Log.e("riendsViewModel", "getFriends: " );
        return friendsRepository.getFriends(getToken());
    }

}
