package ru.brucha.hellochelyabinsk.splash.presenter;

import android.os.Handler;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.brucha.hellochelyabinsk.splash.view.SplashView;

/**
 * Created by prog on 28.12.2017.
 */
@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {
    Handler handler;
    public SplashPresenter() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getViewState().showMainScreen();
            }
        }, 2000);
    }
}
