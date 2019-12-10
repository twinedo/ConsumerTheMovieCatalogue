package com.twinedo.consumerthemoviecatalogue.image;

import android.net.Uri;

import com.twinedo.consumerthemoviecatalogue.api.ServiceURL;

public class ImageParse {

    public static Uri movieUrl(String size, String imagePath) {
        imagePath = imagePath.replace("/", "");

        return Uri.parse(ServiceURL.IMAGE_URL).buildUpon()
                .appendPath(size)
                .appendPath(imagePath)
                .build();
    }
}