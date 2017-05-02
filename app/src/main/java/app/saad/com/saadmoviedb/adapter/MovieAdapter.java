package app.saad.com.saadmoviedb.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.saad.com.saadmoviedb.item.Item;
import app.saad.com.saadmoviedb.R;
import app.saad.com.saadmoviedb.utils.CustomItemClickListener;

/**
 * Created by saad9 on 4/28/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {


    private List<Item> mList;
    Context mContext;
    private CustomItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;
        TextView mTitle,mDate, mRating;
        public MyViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView)itemView.findViewById(R.id.thumbnail);
            mTitle = (TextView)itemView.findViewById(R.id.title);
            mDate = (TextView)itemView.findViewById(R.id.releaseYear);
            mRating = (TextView)itemView.findViewById(R.id.rating);
        }
    }

    public MovieAdapter(Context context, List<Item> list, CustomItemClickListener listener) {
        mContext = context;
        mList = list;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, viewHolder.getAdapterPosition());
            }
        });
        Log.d("HI","Bye");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.MyViewHolder holder, int position) {
        Item item = mList.get(position);
        final String image = "http://image.tmdb.org/t/p/w185/"+item.getPoster_path();

        Glide.with(mContext).load(image).into(holder.mImageView);
        holder.mTitle.setText(item.getTitle());
        holder.mDate.setText("Release Date: "+item.getRelease_date());
        holder.mRating.setText("Rating: "+ item.getVote_average());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
