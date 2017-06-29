package app.saad.com.githubrepo.fragments;

import java.util.List;

import app.saad.com.githubrepo.item.Item;
import app.saad.com.githubrepo.item.Items;

/**
 * Created by saad9 on 6/28/17.
 */

public class PresenterImpl implements Presenter, Model.OnListener{

    RepoListFragmentView view;
    Model model;

    public PresenterImpl(RepoListFragmentView view){
        this.view = view;
        this.model = new ModelImpl();
    }

    @Override
    public void onSuccess(List<Items> myDataset) {
        view.displayListView(myDataset);
    }


    @Override
    public void getListView() {
        model.getList(this);
    }


}
