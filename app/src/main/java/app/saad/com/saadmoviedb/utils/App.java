package app.saad.com.saadmoviedb.utils;

import android.app.Application;

import app.saad.com.saadmoviedb.dagger.component.DaggerNetComponent;
import app.saad.com.saadmoviedb.dagger.component.NetComponent;
import app.saad.com.saadmoviedb.dagger.module.NetModule;

/**
 * Created by saad9 on 5/4/17.
 */

public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.themoviedb.org/3/search/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public Application getApplicationContext(){
        return this;
    }
}
