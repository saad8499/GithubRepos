package app.saad.com.githubrepo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.saad.com.githubrepo.R;
import app.saad.com.githubrepo.item.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by saad9 on 6/28/17.
 */

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.MyViewHolder> {

    private List<Item> mList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.personImage)
        public ImageView mImageView;
        @BindView(R.id.personName)
        public TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public PersonListAdapter(List<Item> list, Context context){
        mList = list;
        mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_list_row, parent, false);
        final PersonListAdapter.MyViewHolder viewHolder = new PersonListAdapter.MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item item = mList.get(position);
        final String image = item.getAvatar_url();

        Glide.with(mContext).load(image).into(holder.mImageView);
        holder.mTextView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
