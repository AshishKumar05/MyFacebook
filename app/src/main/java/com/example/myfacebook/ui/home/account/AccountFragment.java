package com.example.myfacebook.ui.home.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myfacebook.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    FragmentAccountBinding fragmentAccountBinding;
    AccountViewModel accountViewModel;
    int count =0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
    }

    public static Fragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentAccountBinding = FragmentAccountBinding.inflate(getLayoutInflater());
        init();
        fragmentAccountBinding.editQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                fragmentAccountBinding.textView2.setText(String.valueOf(count));
            }
        });
        return fragmentAccountBinding.getRoot();
    }

    private void init() {
        fragmentAccountBinding.textView2.setText("AccountFragment In progress");
    }
}
