package app.saad.com.saadmoviedb.utils;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by saad9 on 5/4/17.
 */

@Module
public class AppModule {
    static Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    public static Application provideApplication() {
        return mApplication;
    }
}
