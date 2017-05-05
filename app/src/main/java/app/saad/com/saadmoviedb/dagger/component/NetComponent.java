package app.saad.com.saadmoviedb.dagger.component;

import javax.inject.Singleton;

import app.saad.com.saadmoviedb.MainActivity;
import app.saad.com.saadmoviedb.dagger.module.NetModule;
import app.saad.com.saadmoviedb.fragments.ModelImpl;
import app.saad.com.saadmoviedb.utils.AppModule;
import dagger.Component;

/**
 * Created by saad9 on 5/4/17.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(ModelImpl activity);
}
