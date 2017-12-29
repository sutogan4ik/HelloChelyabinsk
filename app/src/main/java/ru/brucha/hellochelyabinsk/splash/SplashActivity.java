package ru.brucha.hellochelyabinsk.splash;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import ru.brucha.hellochelyabinsk.MainActivity;
import ru.brucha.hellochelyabinsk.R;
import ru.brucha.hellochelyabinsk.splash.presenter.SplashPresenter;
import ru.brucha.hellochelyabinsk.splash.view.SplashView;

/**
 * Created by prog on 28.12.2017.
 */


public class SplashActivity extends MvpAppCompatActivity implements SplashView{

    @InjectPresenter
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

    }

    @Override
    public void showMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
