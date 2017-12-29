package ru.brucha.hellochelyabinsk.user_list.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.brucha.hellochelyabinsk.entity.user.User;

/**
 * Created by prog on 28.12.2017.
 */

public interface UserListView extends MvpView {
    void showUsers(List<User> users);
    @StateStrategyType(SkipStrategy.class)
    void showError(String error);
    @StateStrategyType(SkipStrategy.class)
    void routeToUserInfo(User user);
    void showProgress();
    void hideProgress();
}
