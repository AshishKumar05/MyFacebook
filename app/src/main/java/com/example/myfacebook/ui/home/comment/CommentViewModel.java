package com.example.myfacebook.ui.home.comment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myfacebook.MyFacebookApplication;
import com.example.myfacebook.data.APIResponse;
import com.example.myfacebook.data.comment.CommentResponse;
import com.example.myfacebook.utils.Constants;

public class CommentViewModel extends AndroidViewModel {

    public CommentRepository commentRepository;
    public MyFacebookApplication app;

    public CommentViewModel(@NonNull Application application) {
        super(application);
        app = (MyFacebookApplication) application;
        commentRepository = new CommentRepository(app.getAPIManager());
    }

    public String getToken() {
        return Constants.token;
    }

    public MutableLiveData<APIResponse<CommentResponse>> get_comment(String postId){
        return commentRepository.get_comment(getToken(),postId);
    }
}
