package com.example.myfacebook.ui.home.mediapost;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myfacebook.MyFacebookApplication;
import com.example.myfacebook.data.APIResponse;
import com.example.myfacebook.data.mediapost.MediaResponse;
import com.example.myfacebook.utils.Constants;

public class MediapostViewModel extends AndroidViewModel {

    public MediapostRepository mediapostRepository;
    public MyFacebookApplication app;

    public MediapostViewModel(@NonNull Application application) {
        super(application);
        app = (MyFacebookApplication) application;
        mediapostRepository = new MediapostRepository(app.getAPIManager());
    }

    public String getToken(){
        return Constants.token;
    }

    public MutableLiveData<APIResponse<MediaResponse>> get_mediapost() {
        Log.e("MediapostViewModel", "get_mediapost: ");
        return mediapostRepository.get_mediapost(getToken());
    }
}