package ru.brucha.hellochelyabinsk.backend;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.brucha.hellochelyabinsk.entity.user_info.Post;
import ru.brucha.hellochelyabinsk.entity.user.User;

/**
 * Created by prog on 28.12.2017.
 */

public interface ApiMethods {

    @GET("users")
    Observable<List<User>> getUsers();

    @GET("posts")
    Observable<List<Post>> getPosts(@Query("userId") long userId);
}
