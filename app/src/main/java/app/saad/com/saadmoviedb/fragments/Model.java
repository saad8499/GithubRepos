package app.saad.com.saadmoviedb.fragments;

import java.util.List;

import app.saad.com.saadmoviedb.item.Item;

/**
 * Created by saad9 on 5/3/17.
 */

public interface Model {


    interface OnListener {
        void onSuccess(List<Item> myDataset);
    }

    interface OnExtendedListViewListener {
        void onExtendedListViewSuccess(List<Item> myDataset);
    }

    void getList(String query, OnListener listener);

    void getNewList(String query, int page, OnExtendedListViewListener listener);

}
