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
import com.twinedo.consumerthemoviecatalogue.api.ServiceURL;
import com.twinedo.consumerthemoviecatalogue.object.Trailer;

import java.util.ArrayList;

public class ListTrailersAdapter extends RecyclerView.Adapter<ListTrailersAdapter.TrailersViewHolder> {

    private ArrayList<Trailer> trailers = new ArrayList<>();
    private OnClickListener onClickListener;

    public void setTrailerData(ArrayList<Trailer> movies) {
        this.trailers.clear();
        this.trailers.addAll(movies);
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trailers, parent, false);
        return new TrailersViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {

        Uri uri = Uri.parse(ServiceURL.YT_THUMBNAIL + trailers.get(position).key + "/0.jpg");

        holder.bind(uri.toString(), trailers.get(position).name);
        holder.setItemClickListener(onClickListener, trailers.get(position));
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    class TrailersViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView thumbnail;

        private TrailersViewHolder(@NonNull final View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_name);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }

        private void bind(String thumbnails, String releaseDate) {
            name.setText(releaseDate);
            Glide.with(itemView).load(thumbnails).centerCrop().into(thumbnail);
        }


        private void setItemClickListener(final OnClickListener onClickListener, final Trailer movie) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onItemClickListener(movie.key, movie);
                }
            });
        }
    }

    public interface OnClickListener {
        void onItemClickListener(String key, Trailer movie);
    }
}
