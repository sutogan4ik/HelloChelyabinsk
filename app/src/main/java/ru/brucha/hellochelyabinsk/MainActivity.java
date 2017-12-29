package ru.brucha.hellochelyabinsk;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.brucha.hellochelyabinsk.entity.user.User;
import ru.brucha.hellochelyabinsk.tools.UserColor;
import ru.brucha.hellochelyabinsk.user_info.UserInfoFragment;
import ru.brucha.hellochelyabinsk.user_list.UserListFragment;

public class MainActivity extends AppCompatActivity implements MainRouter {

    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isTablet = findViewById(R.id.flTabletUserInfo) != null;
        if (savedInstanceState != null) {
            return;
        }
        UserListFragment userListFragment = new UserListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flUserListContainer, userListFragment)
                .commit();
        if (isTablet) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.flTabletUserInfo, UserInfoFragment.newInstance(null))
                    .commit();
        }
    }

    @Override
    public void showUserInfo(User user) {
        UserInfoFragment userInfoFragment = UserInfoFragment.newInstance(user);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isTablet) {
            fragmentTransaction
                    .replace(R.id.flTabletUserInfo, userInfoFragment)
                    .commit();
        } else {
            fragmentTransaction
                    .replace(R.id.flUserListContainer, userInfoFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void resetColors() {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorPrimary)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
    }
}
