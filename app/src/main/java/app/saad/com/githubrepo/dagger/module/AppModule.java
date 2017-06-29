package app.saad.com.githubrepo.dagger.module;

import android.app.Application;

import app.saad.com.githubrepo.dagger.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by saad9 on 6/28/17.
 */

@Module
public class AppModule {
    static Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationScope
    public static Application provideApplication() {
        return mApplication;
    }
}
