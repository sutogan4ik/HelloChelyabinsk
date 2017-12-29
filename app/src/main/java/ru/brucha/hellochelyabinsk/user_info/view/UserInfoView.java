package ru.brucha.hellochelyabinsk.user_info.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import ru.brucha.hellochelyabinsk.entity.user.User;
import ru.brucha.hellochelyabinsk.entity.user_info.Post;

/**
 * Created by prog on 28.12.2017.
 */

public interface UserInfoView extends MvpView {
    void showUserPosts(List<Post> posts);

    void showError(String message);

    void showSelectText();

    void showUserInfo(User user);

    void showProgress();

    void hideProgress();
}
