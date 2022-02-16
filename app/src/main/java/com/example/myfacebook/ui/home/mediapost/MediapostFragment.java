package com.example.myfacebook.ui.home.mediapost;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myfacebook.data.APIResponse;
import com.example.myfacebook.data.comment.CommentResponse;
import com.example.myfacebook.data.comment.Comment;
import com.example.myfacebook.data.mediapost.MediaResponse;
import com.example.myfacebook.data.mediapost.Mediapost;
import com.example.myfacebook.databinding.FragmentMediapostBinding;
import com.example.myfacebook.ui.home.comment.CommentAdapter;
import com.example.myfacebook.ui.home.comment.CommentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MediapostFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    private FragmentMediapostBinding fragmentMediapostBinding;
    private List<Mediapost> mediaList;
    private MediapostViewModel mediapostViewModel;
    private MediapostAdapter mediapostAdapter;
    private CommentViewModel commentViewModel;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList;
    private Mediapost mediapostpop;
    int pageNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediapostViewModel = new ViewModelProvider(this).get(MediapostViewModel.class);
        commentViewModel = new ViewModelProvider(this).get(CommentViewModel.class);
    }

    public static MediapostFragment newInstance() {
        return new MediapostFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        fragmentMediapostBinding = FragmentMediapostBinding.inflate(getLayoutInflater());
        init();
        return fragmentMediapostBinding.getRoot();
    }

    private void init() {
       mediaList = new ArrayList<>();
       mediapostViewModel = new ViewModelProvider(this).get(MediapostViewModel.class);
       SetRecylerView();
       getMediaPost();
    }

    private void SetRecylerView() {
        fragmentMediapostBinding.mediapostItems.setItemAnimator(new DefaultItemAnimator());
        fragmentMediapostBinding.mediapostItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        mediapostAdapter = new MediapostAdapter(getActivity(), mediaList, new MediapostAdapter.itemClickListener() {
            @Override
            public void Itemclick(Mediapost mediapost) {
                commentPopUpdetails(mediapost,-1);
                mediapostpop = mediapost;
            }
        });
        fragmentMediapostBinding.commentDetails.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentPopUpdetails(mediapostpop,1);
            }
        });
        fragmentMediapostBinding.mediapostItems.setAdapter(mediapostAdapter);
        mediapostAdapter.notifyDataSetChanged();
    }

    private void commentPopUpdetails(Mediapost mediapost,int direction) {
        if(direction == -1) {
            fragmentMediapostBinding.mediapostItems.setVisibility(View.VISIBLE);
            fragmentMediapostBinding.commentDetails.commentBackground.setVisibility(View.VISIBLE);
            fragmentMediapostBinding.commentDetails.popup.setVisibility(View.VISIBLE);
        } else {
            fragmentMediapostBinding.mediapostItems.setVisibility(View.VISIBLE);
            fragmentMediapostBinding.commentDetails.commentBackground.setVisibility(View.INVISIBLE);
            fragmentMediapostBinding.commentDetails.popup.setVisibility(View.INVISIBLE);
        }
        initComment(mediapost);
        ObjectAnimator animator = ObjectAnimator.ofFloat(fragmentMediapostBinding.commentDetails.popup,
                "translationY",direction * fragmentMediapostBinding.commentDetails.popup.getHeight());
        animator.setDuration(500);
        animator.start();
    }

    private void initComment(Mediapost mediapost) {
        commentList = new ArrayList<>();
        commentViewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        setCommentRecyclerView();
        getCommentList(mediapost);
    }

    private void setCommentRecyclerView() {
        fragmentMediapostBinding.commentDetails.commentItemList.setItemAnimator(new DefaultItemAnimator());
        fragmentMediapostBinding.commentDetails.commentItemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        commentAdapter = new CommentAdapter(getActivity(),commentList);
        fragmentMediapostBinding.commentDetails.commentItemList.setAdapter(commentAdapter);
        commentAdapter.notifyDataSetChanged();
    }

    private void getCommentList(Mediapost mediapost) {
        commentViewModel.get_comment(mediapost.post_id)
                .observe(getActivity(), new Observer<APIResponse<CommentResponse>>() {
                    @Override
                    public void onChanged(APIResponse<CommentResponse> commentResponseAPIResponse) {
                        if(commentResponseAPIResponse.isError()) {
                            Toast.makeText(getActivity(),"Something error",Toast.LENGTH_LONG).show();
                        } else {
                            CommentResponse commentResponse = commentResponseAPIResponse.getResponse();
                            updateCommentRecyclerView(commentResponse.commentList);
                        }
                    }
                });
    }

    private void updateCommentRecyclerView(List<Comment> resultList) {
        if(resultList.size()==0)
             fragmentMediapostBinding.commentDetails.noResult.setVisibility(View.VISIBLE);
        else
             fragmentMediapostBinding.commentDetails.noResult.setVisibility(View.INVISIBLE);
        this.commentList.addAll(resultList);
        commentAdapter.notifyDataSetChanged();
    }

    private void getMediaPost() {
        fragmentMediapostBinding.loading.view.setVisibility(View.VISIBLE);
        Log.d("MediapostFragment", "getMediaPost: ");
        mediapostViewModel.get_mediapost()
                .observe(getActivity(), new Observer<APIResponse<MediaResponse>>() {
                    @Override
                    public void onChanged(APIResponse<MediaResponse> apiResponse) {
                        fragmentMediapostBinding.loading.view.setVisibility(View.GONE);
                        if(apiResponse.isError()) {
                            Toast.makeText(getActivity(),"Something Error",Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("MediapostFragment", "onChanged: " );
                            MediaResponse response = apiResponse.getResponse();
                            updateRecyclerView(response.MediapostList);

                        }
                    }
                });
    }
    private void updateRecyclerView(List<Mediapost> result){
        Log.d("MediapostFragment", result.size()+"updateRecyclerView: ");
        this.mediaList.addAll(result);
        mediapostAdapter.notifyDataSetChanged();
    }
}