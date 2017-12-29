package ru.brucha.hellochelyabinsk.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.brucha.hellochelyabinsk.backend.ApiMethods;
import ru.brucha.hellochelyabinsk.user_info.model.UserPostsInteractor;
import ru.brucha.hellochelyabinsk.user_info.model.UserPostsInteractorImpl;
import ru.brucha.hellochelyabinsk.user_list.model.UserListInteractor;
import ru.brucha.hellochelyabinsk.user_list.model.UserListInteractorImpl;

/**
 * Created by prog on 28.12.2017.
 */
@Module
public class InteractorsModule {
    @Provides
    UserListInteractor provideUserListInteractor(ApiMethods api){
        return new UserListInteractorImpl(api);
    }

    @Provides
    UserPostsInteractor provideUserPostsInteractor(ApiMethods api){
        return new UserPostsInteractorImpl(api);
    }
}
