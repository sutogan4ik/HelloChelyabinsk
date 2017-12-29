package ru.brucha.hellochelyabinsk.user_info.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.brucha.hellochelyabinsk.R;

/**
 * Created by prog on 28.12.2017.
 */

public class UserPostsViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.tvPostTitle)
    TextView tvPostTitle;
    @BindView(R.id.tvPostText)
    TextView tvPostText;
    @BindView(R.id.vDivider)
    View vDivider;
    public UserPostsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public TextView getTvPostTitle() {
        return tvPostTitle;
    }

    public TextView getTvPostText() {
        return tvPostText;
    }

    public View getvDivider() {
        return vDivider;
    }
}
