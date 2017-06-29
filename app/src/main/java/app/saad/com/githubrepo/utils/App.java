package app.saad.com.githubrepo.utils;

import android.app.Application;

import app.saad.com.githubrepo.dagger.component.DaggerNetComponent;
import app.saad.com.githubrepo.dagger.component.NetComponent;
import app.saad.com.githubrepo.dagger.module.AppModule;
import app.saad.com.githubrepo.dagger.module.NetModule;

/**
 * Created by saad9 on 6/28/17.
 */

public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public Application getApplicationContext(){
        return this;
    }
}
