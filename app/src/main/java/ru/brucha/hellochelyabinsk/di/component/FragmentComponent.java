package ru.brucha.hellochelyabinsk.di.component;

import dagger.Component;
import dagger.Subcomponent;
import ru.brucha.hellochelyabinsk.backend.scope.FragmentScope;
import ru.brucha.hellochelyabinsk.di.module.InteractorsModule;
import ru.brucha.hellochelyabinsk.user_info.UserInfoFragment;
import ru.brucha.hellochelyabinsk.user_list.UserListFragment;

/**
 * Created by prog on 14.01.2018.
 */
@Subcomponent(modules = {InteractorsModule.class})
@FragmentScope
public interface FragmentComponent {
    void inject(UserListFragment userListFragment);
    void inject(UserInfoFragment userInfoFragment);
}
