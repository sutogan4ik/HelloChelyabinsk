package ru.brucha.hellochelyabinsk.user_list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.brucha.hellochelyabinsk.R;

/**
 * Created by prog on 28.12.2017.
 */

public class UserListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvUserMail)
    TextView tvUserMail;
    @BindView(R.id.tvUserCompany)
    TextView tvUserCompany;
    public UserListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getTvUserName() {
        return tvUserName;
    }

    public TextView getTvUserMail() {
        return tvUserMail;
    }

    public TextView getTvUserCompany() {
        return tvUserCompany;
    }
}
