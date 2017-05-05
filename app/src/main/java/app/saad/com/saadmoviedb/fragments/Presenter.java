package app.saad.com.saadmoviedb.fragments;

/**
 * Created by saad9 on 5/3/17.
 */

public interface Presenter {

    void getListView(String query);

    void getNewListView(String query, int page);

}
