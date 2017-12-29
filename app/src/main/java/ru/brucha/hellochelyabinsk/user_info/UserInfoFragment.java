package ru.brucha.hellochelyabinsk.user_info;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.brucha.hellochelyabinsk.MainApplication;
import ru.brucha.hellochelyabinsk.R;
import ru.brucha.hellochelyabinsk.entity.user.User;
import ru.brucha.hellochelyabinsk.entity.user_info.Post;
import ru.brucha.hellochelyabinsk.tools.UserColor;
import ru.brucha.hellochelyabinsk.user_info.adapter.UserPostsAdapter;
import ru.brucha.hellochelyabinsk.user_info.presenter.UserInfoPresenter;
import ru.brucha.hellochelyabinsk.user_info.view.UserInfoView;

/**
 * Created by prog on 28.12.2017.
 */

public class UserInfoFragment extends MvpAppCompatFragment implements UserInfoView{
    private static final String USER_DATA = "user_data";

    public static UserInfoFragment newInstance(User user) {

        Bundle args = new Bundle();
        args.putSerializable(USER_DATA, user);
        UserInfoFragment fragment = new UserInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    @InjectPresenter
    UserInfoPresenter presenter;

    @ProvidePresenter
    UserInfoPresenter provideUserInfoPresenter(){
        return presenter;
    }

    @BindView(R.id.rvUserPosts)
    RecyclerView rvUserPosts;
    @BindView(R.id.tvSelectUser)
    TextView tvSelectUser;
    @BindView(R.id.nsContent)
    View content;
    @BindView(R.id.flUserInfoContainer)
    View flUserInfoContainer;
    @BindView(R.id.tvCompanyName)
    TextView tvCompanyName;
    @BindView(R.id.tvCompanyCatchPhrase)
    TextView tvCompanyCatchPhrase;
    @BindView(R.id.tvCompanyBs)
    TextView tvCompanyBs;
    @BindView(R.id.tvCompany)
    TextView tvCompany;
    @BindView(R.id.tvContacts)
    TextView tvContacts;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvSite)
    TextView tvSite;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.tvStreet)
    TextView tvStreet;
    @BindView(R.id.tvSuite)
    TextView tvSuite;
    @BindView(R.id.cvProgressContainer)
    View progressContainer;
    @BindView(R.id.pbProgress)
    ProgressBar progressBar;
    private UserPostsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        MainApplication.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        adapter = new UserPostsAdapter();
        if(savedInstanceState != null){
            return;
        }
        User user = (User) getArguments().getSerializable(USER_DATA);
        presenter.setUser(user);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_info_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setAutoMeasureEnabled(true);
        rvUserPosts.setLayoutManager(layoutManager);
        rvUserPosts.setAdapter(adapter);
        rvUserPosts.setNestedScrollingEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadPosts();
    }

    @Override
    public void showUserPosts(List<Post> posts) {
        adapter.setPosts(posts);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showSelectText() {
        tvSelectUser.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
    }

    @Override
    public void showUserInfo(User user) {
        int userColor = UserColor.getUserColor400(getContext(), user.getUsername());
        changeColor(user);
        tvCompany.setTextColor(userColor);
        tvContacts.setTextColor(userColor);
        tvAddress.setTextColor(userColor);
        getActivity().setTitle(user.getName());

        tvEmail.setText(user.getEmail());
        tvSite.setText(user.getWebsite());
        tvPhone.setText(user.getPhone());

        tvCompanyName.setText(user.getCompany().getName());
        tvCompanyCatchPhrase.setText(user.getCompany().getCatchPhrase());
        tvCompanyBs.setText(user.getCompany().getBs());

        tvCity.setText(user.getAddress().getCity());
        tvStreet.setText(user.getAddress().getStreet());
        tvSuite.setText(user.getAddress().getSuite());
    }

    private void changeColor(User user){
        int userColor = UserColor.getUserColor400(getContext(), user.getUsername());
        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(userColor));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(userColor);
        }
    }

    @Override
    public void showProgress() {
        progressContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressContainer.setVisibility(View.GONE);
    }
}
