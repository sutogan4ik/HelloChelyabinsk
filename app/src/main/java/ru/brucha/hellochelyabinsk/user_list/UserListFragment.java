package ru.brucha.hellochelyabinsk.user_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.brucha.hellochelyabinsk.MainApplication;
import ru.brucha.hellochelyabinsk.MainRouter;
import ru.brucha.hellochelyabinsk.R;
import ru.brucha.hellochelyabinsk.entity.user.User;
import ru.brucha.hellochelyabinsk.user_list.adapter.UserListAdapter;
import ru.brucha.hellochelyabinsk.user_list.presenter.UserListPresenter;
import ru.brucha.hellochelyabinsk.user_list.view.UserListView;

/**
 * Created by prog on 28.12.2017.
 */

public class UserListFragment extends MvpAppCompatFragment implements UserListView, UserListAdapter.UserClickListener {

    @Inject
    @InjectPresenter
    UserListPresenter presenter;

    @ProvidePresenter
    UserListPresenter provideUserListPresenter(){
        return presenter;
    }

    @BindView(R.id.rvUserList)
    RecyclerView rvUserList;
    @BindView(R.id.cvProgressContainer)
    View progressContainer;

    private UserListAdapter adapter;
    private MainRouter router;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainRouter){
            router = (MainRouter) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        MainApplication.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        adapter = new UserListAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        rvUserList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvUserList.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadUsers();
        getActivity().setTitle(R.string.app_name);
        if(router != null) router.resetColors();
    }

    @Override
    public void showUsers(List<User> users) {
        adapter.setUsers(users);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onUserClick(User user) {
        presenter.clickUser(user);
    }

    @Override
    public void routeToUserInfo(User user) {
        if(router != null) router.showUserInfo(user);
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
