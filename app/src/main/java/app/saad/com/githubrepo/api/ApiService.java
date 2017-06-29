package app.saad.com.githubrepo.api;

import java.util.List;

import app.saad.com.githubrepo.item.OwnerItem;
import app.saad.com.githubrepo.item.PersonList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

        @GET("users/square/repos")
        Call<List<OwnerItem>> getRepos();

        @GET("repos/square/{name}/contributors")
        Call<List<PersonList>> getUsers(@Path("name") String name);


}
