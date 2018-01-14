package ru.brucha.hellochelyabinsk;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.brucha.hellochelyabinsk.di.component.AppComponent;
import ru.brucha.hellochelyabinsk.di.component.DaggerAppComponent;
import ru.brucha.hellochelyabinsk.di.component.FragmentComponent;
import ru.brucha.hellochelyabinsk.di.module.ApiModule;
import ru.brucha.hellochelyabinsk.di.module.InteractorsModule;

/**
 * Created by prog on 28.12.2017.
 */

public class MainApplication extends Application {
    private static AppComponent component;
    public static AppComponent getComponent(){
        return component;
    }
    private static FragmentComponent fragmentComponent;
    public static FragmentComponent getFragmentComponent(){
        return fragmentComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfig);

        component = createComponent();
        fragmentComponent = createFragmentComponent();
    }

    private FragmentComponent createFragmentComponent() {
        return component.plus(new InteractorsModule());
    }

    private AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .apiModule(new ApiModule(BuildConfig.SERVER_URL))
                .build();
    }
}
