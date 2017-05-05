package app.saad.com.saadmoviedb.fragments;

import android.util.Log;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.saad.com.saadmoviedb.item.Item;
import app.saad.com.saadmoviedb.item.MovieList;
import app.saad.com.saadmoviedb.utils.ApiService;
import app.saad.com.saadmoviedb.utils.App;
import app.saad.com.saadmoviedb.utils.AppModule;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by saad9 on 5/4/17.
 */

public class ModelImpl implements Model {

    @Inject
    Retrofit retrofit;

    @Override
    public void getList(String query, final OnListener listener) {

        ((App) AppModule.provideApplication()).getNetComponent().inject(this);

        Call<MovieList> item = retrofit.create(ApiService.class).getImages(query,1);

        item.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                //Set the response to the textview
//                textView.setText(response.body().get(0).getBody());
                listener.onSuccess(getData(response));
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                //Set the error to the textview
//                textView.setText(t.toString());
            }
        });

//        ApiService.Factory.getInstance().getImages(query, 1).enqueue(new Callback<MovieList>() {
//
//            @Override
//            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
//
//                listener.onSuccess(getData(response));
//            }
//            @Override
//            public void onFailure(Call<MovieList> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
    }



    @Override
    public void getNewList(String query, int page, final OnExtendedListViewListener listener) {
//        myDataset.clear();

//        App.getNetComponent().inject(this);

        Call<MovieList> item = retrofit.create(ApiService.class).getImages(query,page);

        item.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                //Set the response to the textview
//                textView.setText(response.body().get(0).getBody());
                listener.onExtendedListViewSuccess(getData(response));
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                //Set the error to the textview
//                textView.setText(t.toString());
            }
        });
//        ApiService.Factory.getInstance().getImages(query, page).enqueue(new Callback<MovieList>() {
//
//            @Override
//            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
//                listener.onExtendedListViewSuccess(getData(response));
//            }
//
//            @Override
//            public void onFailure(Call<MovieList> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
    }

    public List<Item> getData(Response<MovieList> response){
        List<Item> myDataset = new ArrayList<>();
        for (int i = 0; i < response.body().getResults().size();i++) {
            Log.d("ModelIMPL", response.body().getResults().get(i).getId().toString());
            Item item = new Item();
            item.setTitle(response.body().getResults().get(i).getTitle());
            item.setRelease_date(response.body().getResults().get(i).getReleaseDate());
            item.setPoster_path(response.body().getResults().get(i).getPosterPath());
            item.setOverview(response.body().getResults().get(i).getOverview());
            item.setVote_average(response.body().getResults().get(i).getVoteAverage());
            item.setVoteCount(String.valueOf(response.body().getResults().get(i).getVoteCount()));
            item.setLang(response.body().getResults().get(i).getOriginalLanguage());
            myDataset.add(item);
        }
        return myDataset;
    }


}
