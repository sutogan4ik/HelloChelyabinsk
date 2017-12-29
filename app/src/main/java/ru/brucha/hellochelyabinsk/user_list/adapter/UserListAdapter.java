package ru.brucha.hellochelyabinsk.user_list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.brucha.hellochelyabinsk.R;
import ru.brucha.hellochelyabinsk.entity.user.User;
import ru.brucha.hellochelyabinsk.tools.UserColor;

/**
 * Created by prog on 28.12.2017.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListViewHolder> {
    private List<User> users;

    private UserClickListener userClickListener;

    public UserListAdapter(UserClickListener userClickListener) {
        this.userClickListener = userClickListener;
        users = new ArrayList<>();
    }

    @Override
    public UserListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(UserListViewHolder holder, int position) {
        final User user = users.get(position);
        holder.getTvUserName().setText(user.getName());
        holder.getTvUserName().setTextColor(UserColor.getUserColor400(holder.itemView.getContext(), user.getUsername()));
        holder.getTvUserMail().setText(user.getEmail());
        holder.getTvUserCompany().setText(user.getCompany().getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userClickListener != null) userClickListener.onUserClick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public interface UserClickListener{
        void onUserClick(User user);
    }
}
