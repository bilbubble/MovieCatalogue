package com.example.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_SYNOPSIS = "extra_synopsis";
    public static final String EXTRA_IMAGE_URL = "extra_image_url";
    public static final String EXTRA_RELEASE_DATE = "extra_release_date";
    public static final String EXTRA_VOTE = "extra_vote";

    ImageView ivFlyer;
    TextView tvTitle, tvSynopsis, tvRate, tvReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivFlyer = findViewById(R.id.iv_poster_detail);
        tvTitle = findViewById(R.id.tv_title_detail);
        tvSynopsis = findViewById(R.id.tv_synopsis_detail);
        tvRate = findViewById(R.id.tv_vote_detail);
        tvReleaseDate = findViewById(R.id.tv_release_date_detail);

        setMovieData();
    }

    private void setMovieData() {
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String synopsis = getIntent().getStringExtra(EXTRA_SYNOPSIS);
        String imageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        String releaseDate = Util.ToDate(getIntent().getStringExtra(EXTRA_RELEASE_DATE));
        double vote = getIntent().getDoubleExtra(EXTRA_VOTE, 0);

        tvTitle.setText(title);
        tvSynopsis.setText(synopsis);
        tvReleaseDate.setText(releaseDate);
        tvRate.setText(String.valueOf(vote));
        Glide.with(this)
                .load(Const.IMG_URL_500 + imageUrl)
                .into(ivFlyer);

        assert getSupportActionBar() != null : "nothing";
        Util.changeActionBarTitle(this, title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }
}