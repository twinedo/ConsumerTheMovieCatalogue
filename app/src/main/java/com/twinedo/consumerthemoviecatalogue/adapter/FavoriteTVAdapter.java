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
import com.twinedo.consumerthemoviecatalogue.object.TvShows;

import java.util.ArrayList;

public class FavoriteTVAdapter extends RecyclerView.Adapter<FavoriteTVAdapter.FavoriteViewHolder> {

    private final ArrayList<TvShows> listTV = new ArrayList<>();

    public ArrayList<TvShows> getListTV() {
        return listTV;
    }
    private OnClickListener onClickListener;

    public void setListTV(ArrayList<TvShows> listTV) {
        if (listTV.size() > 0 ) {
            this.listTV.clear();
        }
        this.listTV.addAll(listTV);
        notifyDataSetChanged();
    }

    public void setOnClickListener(FavoriteTVAdapter.OnClickListener onClickListener) {
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

        holder.tvTitle.setText(listTV.get(position).getName());
        holder.tvRelease.setText(listTV.get(position).getFirst_air_date());
        holder.tvRate.setText(String.valueOf(listTV.get(position).getVote_average()));

        Uri uri = ImageParse.movieUrl(ImageSize.w500.getValue(), listTV.get(position).getPoster_path());

        holder.loadImage(uri.toString());
        holder.setItemClickListener(onClickListener, listTV.get(position));
    }

    @Override
    public int getItemCount() {
        return listTV.size();
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

        private void setItemClickListener(final OnClickListener onClickListener, final TvShows tvShows) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onItemClickListener(tvShows.id, tvShows);
                }
            });
        }
    }

    public interface OnClickListener {
        void onItemClickListener(long id, TvShows movie);
    }
}
