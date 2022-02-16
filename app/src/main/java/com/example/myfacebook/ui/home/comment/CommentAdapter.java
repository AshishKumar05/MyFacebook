package com.example.myfacebook.ui.home.comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myfacebook.R;
import com.example.myfacebook.data.comment.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {


    List<Comment> list;
    private Context context;

    public CommentAdapter (Context context,List<Comment> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item,parent,false);
        return new CommentAdapter.CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
          holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{

        TextView message,time,authorname;
        ImageView picture;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            authorname = itemView.findViewById(R.id.comment_authorName);
            time = itemView.findViewById(R.id.comment_time);
            message = itemView.findViewById(R.id.comment_text);
            picture = itemView.findViewById(R.id.profile_img);
        }
        public void bind(Comment comment) {
            message.setText(comment.message);
            time.setText(comment.publishDate);
            authorname.setText(comment.author.firstName+""+comment.author.lastName);
            Glide.with(itemView)
                    .load(comment.author.pic_url)
                    .centerCrop()
                    .into(picture);
        }
    }
}
