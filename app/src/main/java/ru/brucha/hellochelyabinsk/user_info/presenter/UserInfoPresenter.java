package ru.brucha.hellochelyabinsk.user_info.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import ru.brucha.hellochelyabinsk.entity.user_info.Post;
import ru.brucha.hellochelyabinsk.entity.user.User;
import ru.brucha.hellochelyabinsk.user_info.model.UserPostsInteractor;
import ru.brucha.hellochelyabinsk.user_info.view.UserInfoView;

/**
 * Created by prog on 28.12.2017.
 */
@InjectViewState
public class UserInfoPresenter extends MvpPresenter<UserInfoView> {

    private UserPostsInteractor interactor;
    private User user;

    @Inject
    public UserInfoPresenter(UserPostsInteractor interactor) {
        this.interactor = interactor;
    }

    public void loadPosts() {
        if(user != null) {
            getViewState().showProgress();
            getViewState().showUserInfo(user);
            interactor.getUserPosts(user.getId(), new UserPostsInteractor.UserPostsListener() {
                @Override
                public void onLoad(List<Post> posts) {
                    getViewState().showUserPosts(posts);
                    getViewState().hideProgress();
                }

                @Override
                public void onError(Throwable error) {
                    getViewState().showError(error.getMessage());
                    getViewState().hideProgress();
                }
            });
        }else{
            getViewState().showSelectText();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        interactor.destroy();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
