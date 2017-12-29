package ru.brucha.hellochelyabinsk.user_info.model;

import java.util.List;

import ru.brucha.hellochelyabinsk.entity.user_info.Post;

/**
 * Created by prog on 28.12.2017.
 */

public interface UserPostsInteractor {
    void getUserPosts(long userId, UserPostsListener listener);
    void destroy();

    interface UserPostsListener{
        void onLoad(List<Post> posts);
        void onError(Throwable error);
    }
}
