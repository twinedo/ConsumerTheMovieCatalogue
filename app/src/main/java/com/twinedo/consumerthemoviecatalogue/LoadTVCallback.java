package com.twinedo.consumerthemoviecatalogue;

import android.database.Cursor;

public interface LoadTVCallback {
    void preExecute();

    void postExecute(Cursor tvShows);
}
