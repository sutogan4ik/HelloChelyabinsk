package ru.brucha.hellochelyabinsk;

import ru.brucha.hellochelyabinsk.entity.user.User;

/**
 * Created by prog on 28.12.2017.
 */

public interface MainRouter {
    void showUserInfo(User user);
    void resetColors();
}
