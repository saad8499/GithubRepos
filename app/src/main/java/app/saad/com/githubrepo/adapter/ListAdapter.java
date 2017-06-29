package app.saad.com.githubrepo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import app.saad.com.githubrepo.item.Items;
import app.saad.com.githubrepo.utils.CustomItemClickListener;
import app.saad.com.githubrepo.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by saad9 on 6/28/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {


    private List<Items> mList;
    Context mContext;
    private CustomItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.language)
        TextView mLanguage;
        @BindView(R.id.thumbnail)
        ImageView mImageView;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.createdAt)
        TextView mCreatedAt;
        @BindView(R.id.watchersCount)
        TextView mWatchersCount ;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public ListAdapter(Context context, List<Items> list, CustomItemClickListener listener) {
        mContext = context;
        mList = list;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_rows, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(itemView);

        itemView.setOnClickListener( (View v) -> {
                listener.onItemClick(v, viewHolder.getAdapterPosition());
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListAdapter.MyViewHolder holder, int position) {
        Items item = mList.get(position);
        final String image = item.getAvatar_url();

        Glide.with(mContext).load(image).into(holder.mImageView);
        holder.mName.setText(item.getName());
        holder.mCreatedAt.setText("Created at: "+item.getCreatedAt());
        holder.mWatchersCount.setText("Watchers Count: "+ item.getWatchersCount());
        holder.mLanguage.setText(item.getLanguage());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
