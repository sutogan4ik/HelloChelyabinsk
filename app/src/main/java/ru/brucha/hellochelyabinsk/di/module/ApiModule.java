package ru.brucha.hellochelyabinsk.di.module;

import android.support.annotation.NonNull;

import java.security.PrivateKey;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import ru.brucha.hellochelyabinsk.backend.ApiMethods;

/**
 * Created by prog on 28.12.2017.
 */
@Module
public class ApiModule {
    private String endPoint;

    public ApiModule(String endPoint) {
        this.endPoint = endPoint;
    }

    @Provides
    @Singleton
    @NonNull
    ApiMethods provideApiMethods(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()
                .create(ApiMethods.class);
    }
}
