package ru.brucha.hellochelyabinsk.user_info.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.brucha.hellochelyabinsk.R;
import ru.brucha.hellochelyabinsk.entity.user_info.Post;

/**
 * Created by prog on 28.12.2017.
 */

public class UserPostsAdapter extends RecyclerView.Adapter<UserPostsViewHolder> {
    private List<Post> posts;

    public UserPostsAdapter() {
        posts = new ArrayList<>();
    }

    @Override
    public UserPostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserPostsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_post_item, parent, false));
    }

    @Override
    public void onBindViewHolder(UserPostsViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.getTvPostTitle().setText(post.getTitle());
        holder.getTvPostText().setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }
}
