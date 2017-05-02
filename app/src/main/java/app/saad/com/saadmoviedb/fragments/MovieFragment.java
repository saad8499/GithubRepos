package app.saad.com.saadmoviedb.fragments;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import app.saad.com.saadmoviedb.DetailActivity;
import app.saad.com.saadmoviedb.R;
import app.saad.com.saadmoviedb.adapter.MovieAdapter;
import app.saad.com.saadmoviedb.item.Item;
import app.saad.com.saadmoviedb.item.MovieList;
import app.saad.com.saadmoviedb.utils.ApiService;
import app.saad.com.saadmoviedb.utils.CustomItemClickListener;
import app.saad.com.saadmoviedb.utils.EndlessScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment implements RecyclerView.OnItemTouchListener {

    private static final String TAG = Application.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    LinearLayoutManager linearLayoutManager;
    private EditText mEditText;
    private Button mButtonSubmit;
    private List<Item> myDataset;
    private InputMethodManager inputManager;
    private String query;
    int page;
    int totalPage;
    private EndlessScrollListener scrollListener;

    private OnFragmentInteractionListener mListener;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_photos, container, false);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.my_recycler_view);

        inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        mLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mEditText = (EditText)v.findViewById(R.id.edit_text);
        mButtonSubmit = (Button)v.findViewById(R.id.button_submit);
        myDataset = new ArrayList<>();
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputManager.isActive()){
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
                page = 1;
                if(!mEditText.getText().toString().equals(null) && !mEditText.getText().toString().equals("")) {
                    query = mEditText.getText().toString();
                    myDataset.clear();
                    scrollListener.resetState();

                ApiService.Factory.getInstance().getImages(query, page).enqueue(new Callback<MovieList>() {

                    @Override
                    public void onResponse(Call<MovieList> call, Response<MovieList> response) {


                        for (int i = 0; i < response.body().getResults().size();i++){
                            Log.d(TAG, response.body().getResults().get(i).getId().toString());
                            Item item = new Item();
                            item.setTitle(response.body().getResults().get(i).getTitle());
                            item.setRelease_date(response.body().getResults().get(i).getReleaseDate());
                            item.setPoster_path(response.body().getResults().get(i).getPosterPath());
                            item.setOverview(response.body().getResults().get(i).getOverview());
                            item.setVote_average(response.body().getResults().get(i).getVoteAverage());
                            item.setVoteCount(String.valueOf(response.body().getResults().get(i).getVoteCount()));
                            item.setLang(response.body().getResults().get(i).getOriginalLanguage());
                            myDataset.add(item);
//                            if(response.body().)
                    }

                        page = response.body().getPage();
                        totalPage = response.body().getTotalPages();

                    mAdapter.notifyDataSetChanged();



            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                t.printStackTrace();
            }
        });

                }else{
                    myDataset.clear();
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        scrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int Whichpage, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
//                loadNextDataFromApi(page);
                if(page < totalPage ) {
                    page = page + 1;
                    ApiService.Factory.getInstance().getImages(query, page).enqueue(new Callback<MovieList>() {

                        @Override
                        public void onResponse(Call<MovieList> call, Response<MovieList> response) {


                            Log.d(TAG, response.raw().toString()+"page:"+page +"totalPage:"+ response.body().getResults().get(0).getOriginalLanguage());
                            for (int i = 0; i < response.body().getResults().size(); i++) {
                                Log.d(TAG, response.body().getResults().get(i).getId().toString());
                                Item item = new Item();
                                item.setTitle(response.body().getResults().get(i).getTitle());
                                item.setRelease_date(response.body().getResults().get(i).getReleaseDate());
                                item.setPoster_path(response.body().getResults().get(i).getPosterPath());
                                item.setVote_average(response.body().getResults().get(i).getVoteAverage());
                                item.setVoteCount(String.valueOf(response.body().getResults().get(i).getVoteCount()));
                                item.setLang(response.body().getResults().get(i).getOriginalLanguage());
                                myDataset.add(item);

                            }

                            page = response.body().getPage();
                            totalPage = response.body().getTotalPages();
                            mAdapter.notifyDataSetChanged();


                        }

                        @Override
                        public void onFailure(Call<MovieList> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }else{
                    Snackbar.make(view, "End of list!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        };

        mRecyclerView.addOnScrollListener(scrollListener);
        mAdapter = new MovieAdapter(getActivity(), myDataset, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Item item = myDataset.get(position);

                Intent newIntent = new Intent(getActivity(), DetailActivity.class);
                Log.d(TAG, item.getLang());
                newIntent.putExtra("Title", item.getTitle());
                newIntent.putExtra("Overview", item.getOverview());
                newIntent.putExtra("PosterPath",item.getPoster_path());
                newIntent.putExtra("ReleaseDate",item.getRelease_date());
                newIntent.putExtra("VoteCount",item.getVoteCount());
                newIntent.putExtra("Language",item.getLang());
                startActivity(newIntent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
