package com.example.myfacebook.ui.home.friends;
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
import com.example.myfacebook.data.friends.FriendsResponse;
import com.example.myfacebook.data.friends.User;
import com.example.myfacebook.databinding.FragmentFriendsBinding;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment{

    private FragmentFriendsBinding fragmentFriendsBinding;
    private FriendsViewModel friendsViewModel;
    private FriendsAdapter friendsAdapter;
    private List<User> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        friendsViewModel = new ViewModelProvider(this).get(FriendsViewModel.class);
    }

    public static Fragment newInstance() {
        return new FriendsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentFriendsBinding = FragmentFriendsBinding.inflate(getLayoutInflater());
        init();
        return fragmentFriendsBinding.getRoot();
    }

    private void init() {
        list = new ArrayList<>();
        friendsViewModel = new ViewModelProvider(this).get(FriendsViewModel.class);
        SetRecylerView();
        getfriendsList();
    }


    private void getfriendsList() {
        fragmentFriendsBinding.loading.view.setVisibility(View.VISIBLE);
        friendsViewModel.getFriends()
                .observe(getActivity(), new Observer<APIResponse<FriendsResponse>>() {
                    @Override
                    public void onChanged(APIResponse<FriendsResponse> apiResponse) {
                        fragmentFriendsBinding.loading.view.setVisibility(View.GONE);
                        if(apiResponse.isError()) {
                            Toast.makeText(getActivity(),"Something Error",Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("MediapostFragment", "onChanged: " );
                            FriendsResponse response = apiResponse.getResponse();
                            updateRecyclerView(response.userList);

                        }
                    }
                });
    }

    private void updateRecyclerView(List<User> resultList) {
            if(resultList.size() == 0)
                fragmentFriendsBinding.textView.setVisibility(View.VISIBLE);
            else
                fragmentFriendsBinding.textView.setVisibility(View.INVISIBLE);
            this.list.addAll(resultList);
            friendsAdapter.notifyDataSetChanged();
    }

    private void SetRecylerView() {
        fragmentFriendsBinding.friendsItems.setItemAnimator(new DefaultItemAnimator());
        fragmentFriendsBinding.friendsItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        friendsAdapter = new FriendsAdapter(getActivity(), list, new FriendsAdapter.itemClickListener() {
            @Override
            public void Itemclick(User user) {
              // commentPopUpdetails (mediapost,-1);
              // mediapostpop = mediapost;
            }
        });
        fragmentFriendsBinding.friendsItems.setAdapter(friendsAdapter);
        friendsAdapter.notifyDataSetChanged();
    }

}
