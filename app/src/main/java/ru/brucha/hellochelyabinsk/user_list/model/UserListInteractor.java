package ru.brucha.hellochelyabinsk.user_list.model;

import java.util.List;

import ru.brucha.hellochelyabinsk.entity.user.User;

/**
 * Created by prog on 28.12.2017.
 */

public interface UserListInteractor {

    void getUsers(UserListListener listListener);
    void destroy();
    interface UserListListener{
        void onLoad(List<User> users);
        void onError(Throwable throwable);
    }
}
