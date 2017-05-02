package app.saad.com.saadmoviedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private TextView title, overview, voteCount, releaseYear, lang;
    private ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView)findViewById(R.id.title);
        overview = (TextView)findViewById(R.id.overview);
        poster = (ImageView)findViewById(R.id.image_view);
        voteCount = (TextView)findViewById(R.id.votecount);
        releaseYear = (TextView)findViewById(R.id.releaseYear);
        lang = (TextView)findViewById(R.id.lang);
        Intent in = getIntent();

        title.setText(in.getStringExtra("Title"));
        overview.setText(in.getStringExtra("Overview"));
        voteCount.setText("Vote Count: "+in.getStringExtra("VoteCount"));
        lang.setText("Original Language: "+in.getStringExtra("Language"));
        releaseYear.setText("Release Date: "+in.getStringExtra("ReleaseDate"));
        Glide.with(this).load("http://image.tmdb.org/t/p/w185/"+in.getStringExtra("PosterPath")).into(poster);
    }
}
