package com.twinedo.consumerthemoviecatalogue;

import android.database.Cursor;

public interface LoadMoviesCallback {
    void preExecute();

    void postExecute(Cursor movies);
}
