package app.saad.com.saadmoviedb.fragments;

import java.util.List;

import app.saad.com.saadmoviedb.item.Item;

/**
 * Created by saad9 on 5/4/17.
 */

public class PresenterImpl implements Presenter, Model.OnListener, Model.OnExtendedListViewListener {

    MovieFragmentView view;
    Model model;

    public PresenterImpl(MovieFragmentView view){
        this.view = view;
        this.model = new ModelImpl();
    }

    @Override
    public void onSuccess(List<Item> myDataset) {
        view.displayListView(myDataset);
    }


    @Override
    public void getListView(String query) {
        model.getList(query, this);
    }

    @Override
    public void getNewListView(String query, int page) {
        model.getNewList(query, page, this);
    }

    @Override
    public void onExtendedListViewSuccess(List<Item> myDataset) {
        view.displayExtendedListView(myDataset);
    }
}
