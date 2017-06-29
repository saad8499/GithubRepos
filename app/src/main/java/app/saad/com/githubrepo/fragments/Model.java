package app.saad.com.githubrepo.fragments;

import java.util.List;

import app.saad.com.githubrepo.item.Item;
import app.saad.com.githubrepo.item.Items;

public interface Model {


    interface OnListener {
        void onSuccess(List<Items> myDataset);
    }

    void getList(OnListener listener);

}
