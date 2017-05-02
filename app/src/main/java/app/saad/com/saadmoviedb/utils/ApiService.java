package app.saad.com.saadmoviedb.utils;

import app.saad.com.saadmoviedb.item.MovieList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by saad9 on 4/28/17.
 */

public interface ApiService {

        String BASE_URL = "https://api.themoviedb.org/3/search/";

        @GET("movie?api_key=c0a3ce953702fbbce6be9853ad3bfda4")
        Call<MovieList> getImages(@Query("query") String repo, @Query("page") int page);

        class Factory{

            private static ApiService service;
            public static ApiService getInstance(){
                if(service == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(BASE_URL)
                            .build();

                    service = retrofit.create(ApiService.class);
                    return  service;
                }else{
                    return service;
                }
            }
        }

}
