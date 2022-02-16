package com.example.myfacebook.ui.home.friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfacebook.R;
import com.example.myfacebook.data.friends.User;


import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder> {

    Context context;
    private List<User> userList;
    FriendsAdapter.itemClickListener listener;

    public FriendsAdapter (FragmentActivity context, List<User> userList, FriendsAdapter.itemClickListener listener) {
        this.context = context;
        this.userList = userList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public FriendsAdapter.FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_friends_item,parent,false);
        return new FriendsAdapter.FriendsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsAdapter.FriendsViewHolder holder, int position) {
        holder.bind(userList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }


    public class FriendsViewHolder extends RecyclerView.ViewHolder {

        ImageView profile;
        TextView name,location;
        public FriendsViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.user_profile_img);
            name = itemView.findViewById(R.id.user_name);
            location = itemView.findViewById(R.id.location);
        }

        public void bind(User user, FriendsAdapter.itemClickListener listener) {

            name.setText(user.title+" "+user.firstName+" "+user.lastName);
            if(user.location != null)
               location.setText(user.location.street+" "+user.location.city+" "+user.location.state+" "+user.location.country);
            Glide.with(itemView)
                    .load(user.url)
                    .placeholder(R.drawable.ic_user)
                    .centerCrop()
                    .into(profile);

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.Itemclick(user);
                }
            });
        }
    }

    public interface itemClickListener{

        public void Itemclick(User user);
    }
}
