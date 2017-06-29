package app.saad.com.githubrepo;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import app.saad.com.githubrepo.adapter.PersonListAdapter;
import app.saad.com.githubrepo.dagger.module.AppModule;
import app.saad.com.githubrepo.item.Item;
import app.saad.com.githubrepo.item.PersonList;
import app.saad.com.githubrepo.api.ApiService;
import app.saad.com.githubrepo.utils.App;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/*

SHOWING THE DETAILS OF THE REPOSITORIES, AND THE PERSON WHO CONTRIBUTED IN A RECYLER VIEW

 */
public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.description)
    TextView mDescription;
    @BindView(R.id.thumbnail)
    ImageView mThumbnail;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayoutManager linearLayoutManager;

    private List<Item> myDataset;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);
        Intent in = getIntent();

        ((App) AppModule.provideApplication()).getNetComponent().inject(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager = new LinearLayoutManager(this);
        myDataset = new ArrayList<>();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PersonListAdapter(myDataset, DetailActivity.this);
        mRecyclerView.setAdapter(mAdapter);


        mName.setText(in.getStringExtra("Name"));
        mDescription.setText(in.getStringExtra("Description"));
        Glide.with(this).load(in.getStringExtra("AvatarUrl")).into(mThumbnail);



        Call<List<PersonList>> item = retrofit.create(ApiService.class).getUsers(in.getStringExtra("Name"));

        item.enqueue(new Callback<List<PersonList>>() {
            @Override
            public void onResponse(Call<List<PersonList>> call, Response<List<PersonList>> response) {

                for(int i = 0; i< response.body().size();i++){
                    Item item = new Item();
                    Log.d("TAG", response.body().get(i).getAvatarUrl());
                    item.setAvatar_url(response.body().get(i).getAvatarUrl());
                    item.setName(response.body().get(i).getLogin());
                    myDataset.add(item);
                }
                    mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<PersonList>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}
