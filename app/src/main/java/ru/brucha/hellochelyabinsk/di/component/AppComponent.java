package ru.brucha.hellochelyabinsk.di.component;


import javax.inject.Singleton;

import dagger.Component;
import ru.brucha.hellochelyabinsk.di.module.ApiModule;
import ru.brucha.hellochelyabinsk.di.module.InteractorsModule;
import ru.brucha.hellochelyabinsk.user_info.UserInfoFragment;
import ru.brucha.hellochelyabinsk.user_list.UserListFragment;

/**
 * Created by prog on 28.12.2017.
 */
@Component(modules = {ApiModule.class})
@Singleton
public interface AppComponent {
    FragmentComponent plus(InteractorsModule interactorsModule);

}
