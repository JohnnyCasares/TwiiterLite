package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;

    //PASS IN THE CONTEXT AND LIST OF TWEETS
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    //FOR EACH ROW, INFLATE THE LAYOUT
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    //BIND VALUES BASED ON THE POSITION OF THE ELEMENT
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //GET THE DATA at position
        Tweet tweet = tweets.get(position);
        //BIND THE TWEET WITH THE VIEW HOLDER
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }
    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }


    //DEFINE A VIEWHOLDER
    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView fecha;
        ImageView ivProfilePic;
        TextView tvScreenName;
        TextView tvBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfilePic = itemView.findViewById(R.id.ivProfilePic);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvBody = itemView.findViewById(R.id.tvBody);
            fecha = itemView.findViewById(R.id.created_at);

        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            fecha.setText(tweet.createdAt.substring(0,10));
            Glide.with(context).load(tweet.user.profileImageURL).into(ivProfilePic);

        }
    }
}
