package app.saad.com.githubrepo.fragments;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.saad.com.githubrepo.dagger.module.AppModule;
import app.saad.com.githubrepo.item.Items;
import app.saad.com.githubrepo.item.OwnerItem;
import app.saad.com.githubrepo.api.ApiService;
import app.saad.com.githubrepo.utils.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by saad9 on 6/28/17.
 */

public class ModelImpl implements Model {

    @Inject
    Retrofit retrofit;

    @Override
    public void getList(final OnListener listener) {

        ((App) AppModule.provideApplication()).getNetComponent().inject(this);
        Call<List<OwnerItem>> item = retrofit.create(ApiService.class).getRepos();

        item.enqueue(new Callback<List<OwnerItem>>() {
            @Override
            public void onResponse(Call<List<OwnerItem>> call, Response<List<OwnerItem>> response) {

                listener.onSuccess(getData(response));
            }

            @Override
            public void onFailure(Call<List<OwnerItem>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public List<Items> getData(Response<List<OwnerItem>> response){
        List<Items> myDataset = new ArrayList<>();
        for (int i = 0; i < response.body().size();i++) {
            Items item = new Items();
            item.setName(response.body().get(i).getName());
            item.setCreatedAt(response.body().get(i).getCreatedAt());
            item.setCollaboratorsUrl(response.body().get(i).getCollaboratorsUrl());
            item.setWatchersCount(String.valueOf(response.body().get(i).getWatchersCount()));
            item.setDescription(response.body().get(i).getDescription());
            item.setLanguage(response.body().get(i).getLanguage());
            item.setId(String.valueOf(response.body().get(i).getId()));
            item.setAvatar_url(response.body().get(i).getOwner().getAvatarUrl());
            myDataset.add(item);
        }
        return myDataset;
    }


}
