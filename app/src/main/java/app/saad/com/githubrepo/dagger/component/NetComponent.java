package app.saad.com.githubrepo.dagger.component;

import javax.inject.Singleton;

import app.saad.com.githubrepo.DetailActivity;
import app.saad.com.githubrepo.dagger.ApplicationScope;
import app.saad.com.githubrepo.dagger.module.AppModule;
import app.saad.com.githubrepo.dagger.module.NetModule;
import app.saad.com.githubrepo.fragments.ModelImpl;
import dagger.Component;

/**
 * Created by saad9 on 6/28/17.
 */

@ApplicationScope
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(ModelImpl activity);
    void inject(DetailActivity activity);
}
