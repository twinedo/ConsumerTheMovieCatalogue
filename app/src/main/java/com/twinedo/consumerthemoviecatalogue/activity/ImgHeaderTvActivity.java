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
import com.twinedo.consumerthemoviecatalogue.object.TvShows;

public class ImgHeaderTvActivity extends Activity {

    public static final String EXTRA_PHOTO = "extra_photo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_header);

        final TvShows tvShows = getIntent().getParcelableExtra(EXTRA_PHOTO);

        ImageView close = findViewById(R.id.close);
        ImageView imgHeader = findViewById(R.id.imageHeader);

        if (tvShows != null) {
            Uri uri = ImageParse.movieUrl(ImageSize.w500.getValue(), tvShows.getPoster_path());
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
