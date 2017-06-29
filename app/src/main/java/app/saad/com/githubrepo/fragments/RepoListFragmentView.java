package app.saad.com.githubrepo.fragments;

import java.util.List;

import app.saad.com.githubrepo.item.Item;
import app.saad.com.githubrepo.item.Items;

/**
 * Created by saad9 on 6/28/17.
 */

public interface RepoListFragmentView {

    void displayListView(List<Items> myDataSet);
}
