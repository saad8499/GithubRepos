package app.saad.com.saadmoviedb.fragments;

import java.util.List;

import app.saad.com.saadmoviedb.item.Item;

/**
 * Created by saad9 on 5/4/17.
 */

public interface MovieFragmentView {

    void displayListView(List<Item> myDataSet);

    void displayExtendedListView(List<Item> myDataset);
}
