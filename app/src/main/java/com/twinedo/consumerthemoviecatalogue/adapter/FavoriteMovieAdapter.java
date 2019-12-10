package com.twinedo.consumerthemoviecatalogue.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.twinedo.consumerthemoviecatalogue.R;
import com.twinedo.consumerthemoviecatalogue.image.ImageParse;
import com.twinedo.consumerthemoviecatalogue.image.ImageSize;
import com.twinedo.consumerthemoviecatalogue.object.Movie;

import java.util.ArrayList;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteViewHolder> {

    // private static final String TAG = FavoriteMovieAdapter.class.getSimpleName();

    private final ArrayList<Movie> listMovie = new ArrayList<>();

    public ArrayList<Movie> getListMovie() {
        return listMovie;
    }
    private OnClickListener onClickListener;

    public void setListMovie(ArrayList<Movie> listMovie) {
        if (listMovie.size() > 0 ) {
            this.listMovie.clear();
        }
        this.listMovie.addAll(listMovie);
        notifyDataSetChanged();
    }

    public void setOnClickListener(FavoriteMovieAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {

        holder.tvTitle.setText(listMovie.get(position).getTitle());
        holder.tvRelease.setText(listMovie.get(position).getRelease_date());
        holder.tvRate.setText(String.valueOf(listMovie.get(position).getVote_average()));

        Uri uri = ImageParse.movieUrl(ImageSize.w500.getValue(), listMovie.get(position).getPoster_path());
        holder.loadImage(uri.toString());

        holder.setItemClickListener(onClickListener, listMovie.get(position));
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvRelease;
        private TextView tvRate;
        private ImageView imgPoster;

        FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.movie_title);
            tvRelease = itemView.findViewById(R.id.movie_release);
            tvRate = itemView.findViewById(R.id.movie_rate);
            imgPoster = itemView.findViewById(R.id.movie_poster);
        }

        private void loadImage(String imgUrl){
            Glide.with(itemView).load(imgUrl).centerCrop().into(imgPoster);
        }

        private void setItemClickListener(final OnClickListener onClickListener, final Movie movie) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onItemClickListener(movie.id, movie);
                }
            });
        }
    }

    public interface OnClickListener {
        void onItemClickListener(long id, Movie movie);
    }
}
