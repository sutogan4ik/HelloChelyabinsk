package ru.brucha.hellochelyabinsk.user_info.model;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import ru.brucha.hellochelyabinsk.backend.ApiMethods;
import ru.brucha.hellochelyabinsk.entity.user_info.Post;

/**
 * Created by prog on 28.12.2017.
 */

public class UserPostsInteractorImpl implements UserPostsInteractor {
    private ApiMethods api;
    private CompositeDisposable disposable;

    @Inject
    public UserPostsInteractorImpl(ApiMethods api) {
        this.api = api;
        disposable = new CompositeDisposable();
    }

    @Override
    public void getUserPosts(long userId, final UserPostsListener listener) {
        disposable.add(api.getPosts(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<Post>, List<Post>>() {
                    @Override
                    public List<Post> apply(List<Post> posts) throws Exception {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.insertOrUpdate(posts);
                        realm.commitTransaction();
                        return posts;
                    }
                })
                .mergeWith(Observable.just(getPostCache(userId)))
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> posts) throws Exception {
                        if(listener != null) listener.onLoad(posts);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(listener != null) listener.onError(throwable);
                    }
                }));
    }

    private List<Post> getPostCache(long userId){
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Post.class).equalTo("userId", userId).findAll();
    }

    @Override
    public void destroy() {
        disposable.clear();
        disposable.dispose();
    }
}
