package ru.brucha.hellochelyabinsk.user_list.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import ru.brucha.hellochelyabinsk.entity.user.User;
import ru.brucha.hellochelyabinsk.user_list.model.UserListInteractor;
import ru.brucha.hellochelyabinsk.user_list.view.UserListView;

/**
 * Created by prog on 28.12.2017.
 */
@InjectViewState
public class UserListPresenter extends MvpPresenter<UserListView> {


    private UserListInteractor interactor;

    @Inject
    public UserListPresenter(UserListInteractor interactor) {
        this.interactor = interactor;
    }

    public void loadUsers(){
        getViewState().showProgress();
        interactor.getUsers(new UserListInteractor.UserListListener() {
            @Override
            public void onLoad(List<User> users) {
                getViewState().showUsers(users);
                getViewState().hideProgress();
            }

            @Override
            public void onError(Throwable throwable) {
                getViewState().showError(throwable.getMessage());
                getViewState().hideProgress();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        interactor.destroy();
    }

    public void clickUser(User user) {
        getViewState().routeToUserInfo(user);
    }
}
