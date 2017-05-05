package app.saad.com.saadmoviedb.fragments;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
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

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


import app.saad.com.saadmoviedb.DetailActivity;
import app.saad.com.saadmoviedb.R;
import app.saad.com.saadmoviedb.adapter.MovieAdapter;
import app.saad.com.saadmoviedb.item.Item;
import app.saad.com.saadmoviedb.item.MovieList;
import app.saad.com.saadmoviedb.utils.ApiService;
import app.saad.com.saadmoviedb.utils.CustomItemClickListener;
import app.saad.com.saadmoviedb.utils.EndlessScrollListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment implements MovieFragmentView, CustomItemClickListener{

    private static final String TAG = Application.class.getSimpleName();

    @BindView(R.id.my_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.edit_text) EditText mEditText;
    @BindView(R.id.button_submit) Button mButtonSubmit;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayoutManager linearLayoutManager;

    private List<Item> myDataset;
    private InputMethodManager inputManager;
    private String query;
    int page;
    int totalPage;
    private EndlessScrollListener scrollListener;

    Presenter presenter;

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

        ButterKnife.bind(this, v);
        inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        presenter = new PresenterImpl(this);


        mLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        myDataset = new ArrayList<>();
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputManager.isActive()){
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }

                if(!mEditText.getText().toString().equals(null) && !mEditText.getText().toString().equals("")) {
                    query = mEditText.getText().toString();
                    myDataset.clear();
                    scrollListener.resetState();
                    presenter.getListView(query);
                }else{
                    myDataset.clear();
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        scrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int Whichpage, int totalItemsCount, RecyclerView view) {
//                if (page < totalPage) {
                    page = Whichpage + 1;

                    presenter.getNewListView(query,page);
//                }
            }
        };

        mRecyclerView.addOnScrollListener(scrollListener);


        return v;
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
    public void displayListView(List<Item> myDataSet) {
//        this.myDataset.clear();
        this.myDataset = myDataSet;
        mAdapter = new MovieAdapter(getActivity(), this.myDataset, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void displayExtendedListView(List<Item> myDataset) {
        this.myDataset.addAll(myDataset);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View v, int position) {
        Item item = myDataset.get(position);
        Intent newIntent = new Intent(getActivity(), DetailActivity.class);
        newIntent.putExtras(createBundle(item));
        startActivity(newIntent);
    }

    public Bundle createBundle(Item item){
        Bundle bundle = new Bundle();
        bundle.putString("Title",item.getTitle());
        bundle.putString("Overview", item.getOverview());
        bundle.putString("PosterPath",item.getPoster_path());
        bundle.putString("ReleaseDate",item.getRelease_date());
        bundle.putString("VoteCount",item.getVoteCount());
        bundle.putString("Language",item.getLang());
        return bundle;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private final CompositeDisposable disposables = new CompositeDisposable();

    void onRunSchedulerExampleButtonClicked() {
        disposables.add(sampleObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override public void onComplete() {
                        Log.d(TAG, "onComplete()");
                    }

                    @Override public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                    }

                    @Override public void onNext(String string) {
                        Log.d(TAG, "onNext(" + string + ")");
                    }
                }));
    }

    static Observable<String> sampleObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override public ObservableSource<? extends String> call() throws Exception {
                // Do some long running operation
                SystemClock.sleep(5000);
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }

}
