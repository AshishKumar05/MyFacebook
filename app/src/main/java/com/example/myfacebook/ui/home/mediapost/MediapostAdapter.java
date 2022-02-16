package com.example.myfacebook.ui.home.mediapost;

import android.content.Context;
import android.net.Uri;
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
import com.example.myfacebook.data.mediapost.Mediapost;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import retrofit2.http.Url;

public class MediapostAdapter extends RecyclerView.Adapter<MediapostAdapter.MediapostViewHolder> {

    Context context;
    private List<Mediapost> postList;
    itemClickListener listener;

    public MediapostAdapter (FragmentActivity context, List<Mediapost> postList,itemClickListener listener) {
        this.context = context;
        this.postList = postList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public MediapostAdapter.MediapostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_mediapost_item,parent,false);
        return new MediapostAdapter.MediapostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediapostAdapter.MediapostViewHolder holder, int position) {
          holder.bind(postList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return postList == null ? 0 : postList.size();
    }


    public class MediapostViewHolder extends RecyclerView.ViewHolder {

        ImageView profile,picture;
        TextView name,time,tagtext,description,like,comment;
        public MediapostViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.profile_img);
            picture = itemView.findViewById(R.id.media);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            tagtext = itemView.findViewById(R.id.tag);
            description = itemView.findViewById(R.id.description);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
        }

        public void bind(Mediapost mediapost,itemClickListener listener) {
            name.setText(mediapost.author.firstName+""+mediapost.author.lastName);
            description.setText(mediapost.description);
            like.setText(mediapost.like+" Likes");
            String tag="";
            for(int i=0;i<mediapost.tags.size();i++) {
                tag = tag +"  "+mediapost.tags.get(i);
            }
            tagtext.setText(tag);
            time.setText(mediapost.date);
            Glide.with(itemView)
                    .load(mediapost.author.pic_url)
                    .placeholder(R.drawable.ic_user)
                    .centerCrop()
                    .into(profile);



             Uri uri = Uri.parse("http://192.168.104.95:8080/Image/IMG-20200322-WA0003.jpg");
             //     URI  myURL = new URI("http://192.168.104.95:8080/Image/IMG-20200322-WA0003.jpg");
                  Glide.with(itemView)
                          .load(uri)
                          .centerCrop()
                          .into(picture);



            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.Itemclick(mediapost);
                }
            });
        }
    }

    public interface itemClickListener{

        public void Itemclick(Mediapost mediapost);
    }

}
