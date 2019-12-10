package com.twinedo.consumerthemoviecatalogue.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.twinedo.consumerthemoviecatalogue.LoadTVCallback;
import com.twinedo.consumerthemoviecatalogue.R;
import com.twinedo.consumerthemoviecatalogue.activity.DetailTvShowsActivity;
import com.twinedo.consumerthemoviecatalogue.adapter.FavoriteTVAdapter;
import com.twinedo.consumerthemoviecatalogue.object.TvShows;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.twinedo.consumerthemoviecatalogue.db.DatabaseContract.FavoriteColumns.CONTENT_URI_TV;
import static com.twinedo.consumerthemoviecatalogue.helper.MappingHelper.mapCursorTVToArrayList;

public class FavTVFragment extends Fragment implements LoadTVCallback {

    private FavoriteTVAdapter favoriteTVAdapter = new FavoriteTVAdapter();

    private ProgressBar loadingBar;
    private TextView textFavTV;

    private static final String EXTRA_STATE = "EXTRA_STATE";

    public FavTVFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadingBar = view.findViewById(R.id.loading);
        textFavTV = view.findViewById(R.id.textFavTV);
        textFavTV.setText(R.string.textFavMovie);

        showLoading(true);

        RecyclerView rvFavTV = view.findViewById(R.id.rv_fav_tv);
        rvFavTV.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavTV.setHasFixedSize(true);
        rvFavTV.setAdapter(favoriteTVAdapter);
        favoriteTVAdapter.notifyDataSetChanged();
        favoriteTVAdapter.setOnClickListener(new FavoriteTVAdapter.OnClickListener() {
            @Override
            public void onItemClickListener(long id, TvShows tvShows) {
                gotoDetail(id, tvShows);
            }
        });

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver tvObserver = new DataObserver(handler, getContext());
        if (getContext() != null)
            getContext().getContentResolver()
                    .registerContentObserver(CONTENT_URI_TV, true, tvObserver);

        if (savedInstanceState == null) {
            new LoadMoviesAsync(getContext(), this).execute();
            
        } else {
            ArrayList<TvShows> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            showLoading(false);
            assert list != null;
            if (list.isEmpty()) {
                textFavTV.setVisibility(View.VISIBLE);
            } else {
                textFavTV.setVisibility(View.GONE);
                favoriteTVAdapter.setListTV(list);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, favoriteTVAdapter.getListTV());
    }

    @Override
    public void preExecute() {
        showLoading(true);
    }

    @Override
    public void postExecute(Cursor tvShows) {
        showLoading(false);

        ArrayList<TvShows> listTvShows = mapCursorTVToArrayList(tvShows);

        if (listTvShows.size() > 0) {
            favoriteTVAdapter.setListTV(listTvShows);
            textFavTV.setVisibility(View.GONE);
        } else {
            textFavTV.setVisibility(View.VISIBLE);
            favoriteTVAdapter.setListTV(new ArrayList<TvShows>());
        }
    }

    private static class LoadMoviesAsync extends AsyncTask<Void, Void, Cursor> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadTVCallback> weakCallback;

        private LoadMoviesAsync(Context context, LoadTVCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Context context = weakContext.get();
            return context.getContentResolver().query(CONTENT_URI_TV, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor tvShows) {
            super.onPostExecute(tvShows);
            weakCallback.get().postExecute(tvShows);
        }
    }

    private void showLoading(Boolean state) {
        if (state) {
            loadingBar.setVisibility(View.VISIBLE);
        } else {
            loadingBar.setVisibility(View.GONE);
        }
    }

    private void gotoDetail(long id, TvShows tvShows){
        String data = new Gson().toJson(tvShows);

        Intent intent = new Intent(getActivity(), DetailTvShowsActivity.class);
        intent.putExtra("tv_id", id);
        intent.putExtra("tv_data", data);
        if (getActivity() != null) {
            getActivity().startActivity(intent);
        }
    }

    public static class DataObserver extends ContentObserver {

        final Context context;

        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadMoviesAsync(context, (LoadTVCallback) context).execute();
        }
    }
}
