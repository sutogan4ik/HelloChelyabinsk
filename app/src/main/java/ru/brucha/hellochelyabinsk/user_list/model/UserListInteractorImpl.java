package ru.brucha.hellochelyabinsk.user_list.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import ru.brucha.hellochelyabinsk.backend.ApiMethods;
import ru.brucha.hellochelyabinsk.entity.user.User;

/**
 * Created by prog on 28.12.2017.
 */

public class UserListInteractorImpl implements UserListInteractor {

    private ApiMethods api;

    private CompositeDisposable disposable;
    private Realm realm;

    @Inject
    public UserListInteractorImpl(ApiMethods api) {
        this.api = api;
        disposable = new CompositeDisposable();
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void getUsers(final UserListListener listListener) {
        disposable.add(api.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<User>, List<User>>() {
                    @Override
                    public List<User> apply(List<User> users) throws Exception {
                        realm.beginTransaction();
                        realm.insertOrUpdate(users);
                        realm.commitTransaction();
                        return users;
                    }
                })
                .mergeWith(Observable.just(getUsersCache()))
                .map(new Function<List<User>, List<User>>() {
                    @Override
                    public List<User> apply(List<User> users) throws Exception {
                        Collections.sort(users, new Comparator<User>() {
                            @Override
                            public int compare(User o1, User o2) {
                                return o1.getUsername().compareTo(o2.getUsername());
                            }
                        });
                        return users;
                    }
                })
                .subscribe(
                new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        if(listListener != null) listListener.onLoad(users);
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        if(listListener != null) listListener.onError(throwable);
                    }
                }
        ));
    }

    private List<User> getUsersCache(){
        return realm.copyFromRealm(realm.where(User.class).findAll());
    }

    @Override
    public void destroy() {
        disposable.clear();
        disposable.dispose();
        realm.close();
    }
}
