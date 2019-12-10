package com.twinedo.consumerthemoviecatalogue.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.twinedo.consumerthemoviecatalogue.R;
import com.twinedo.consumerthemoviecatalogue.image.ImageParse;
import com.twinedo.consumerthemoviecatalogue.image.ImageSize;
import com.twinedo.consumerthemoviecatalogue.object.Movie;

public class ImgHeaderMovieActivity extends Activity {

    public static final String EXTRA_PHOTO = "extra_photo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_header);

        final Movie movie = getIntent().getParcelableExtra(EXTRA_PHOTO);

        ImageView close = findViewById(R.id.close);
        ImageView imgHeader = findViewById(R.id.imageHeader);

        if (movie != null) {
            Uri uri = ImageParse.movieUrl(ImageSize.w500.getValue(), movie.getPoster_path());
            Glide.with(this).load(uri).centerCrop().into(imgHeader);
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
