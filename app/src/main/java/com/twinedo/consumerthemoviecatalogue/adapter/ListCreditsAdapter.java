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
import com.twinedo.consumerthemoviecatalogue.image.ImageSize;
import com.twinedo.consumerthemoviecatalogue.object.Credits;

import java.util.ArrayList;

public class ListCreditsAdapter extends RecyclerView.Adapter<ListCreditsAdapter.CreditsViewHolder> {

    private ArrayList<Credits> credits = new ArrayList<>();

    public void setCreditsData(ArrayList<Credits> credits) {
        this.credits.clear();
        this.credits.addAll(credits);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CreditsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_credits, parent, false);
        return new CreditsViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditsViewHolder holder, int position) {

        Uri uri = Uri.parse(ServiceURL.IMAGE_URL + ImageSize.w500.getValue() + "/" + credits.get(position).getProfile_path());

        holder.bind(uri.toString());
        holder.name.setText(credits.get(position).getName());
        holder.character.setText(credits.get(position).getCharacter());

    }

    @Override
    public int getItemCount() {
        return credits.size();
    }

    class CreditsViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView character;
        private ImageView castImage;

        private CreditsViewHolder(@NonNull final View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.namaAsli);
            character = itemView.findViewById(R.id.namaChar);
            castImage = itemView.findViewById(R.id.castPic);
        }

        private void bind(String profileUrl) {
            Glide.with(itemView).load(profileUrl).centerCrop().into(castImage);
        }
    }


}
