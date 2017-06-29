package app.saad.com.githubrepo.fragments;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import app.saad.com.githubrepo.DetailActivity;
import app.saad.com.githubrepo.adapter.ListAdapter;
import app.saad.com.githubrepo.item.Items;
import app.saad.com.githubrepo.utils.CustomItemClickListener;
import app.saad.com.githubrepo.R;
import butterknife.BindView;
import butterknife.ButterKnife;


/*

USING BASIC MVP ARCHITECTURE TO DISPLAY THE REPOSITORIES IN A RECYCLERVIEW

 */

public class RepoListFragment extends Fragment implements RepoListFragmentView, CustomItemClickListener {

    private static final String TAG = Application.class.getSimpleName();

    @BindView(R.id.my_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.edit_text) EditText mEditText;
    @BindView(R.id.button_submit) Button mButtonSubmit;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayoutManager linearLayoutManager;

    private List<Items> myDataset;

    Presenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PresenterImpl(this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        myDataset = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_photos, container, false);

        ButterKnife.bind(this, v);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        myDataset.clear();
        presenter.getListView();

        return v;
    }

    @Override
    public void displayListView(List<Items> myDataSet) {
        this.myDataset = myDataSet;
        mAdapter = new ListAdapter(getActivity(), this.myDataset, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View v, int position) {
        Items item = myDataset.get(position);
        Intent newIntent = new Intent(getActivity(), DetailActivity.class);
        newIntent.putExtras(createBundle(item));
        startActivity(newIntent);
    }

    public Bundle createBundle(Items item){
        Bundle bundle = new Bundle();
        bundle.putString("Name",item.getName());
        bundle.putString("Description", item.getDescription());
        bundle.putString("CollaboratorsUrl",item.getCollaboratorsUrl());
        bundle.putString("AvatarUrl",item.getAvatar_url());
        return bundle;
    }

}
