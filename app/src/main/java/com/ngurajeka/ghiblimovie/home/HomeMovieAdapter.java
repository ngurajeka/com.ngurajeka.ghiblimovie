package com.ngurajeka.ghiblimovie.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ngurajeka.ghiblimovie.R;
import com.ngurajeka.ghiblimovie.model.Film;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

@SuppressWarnings("WeakerAccess")
public class HomeMovieAdapter extends RecyclerView.Adapter<HomeMovieAdapter.ViewHolder> {

    private List<Film> films;
    private HomeFragment.FilmItemListener mListener;

    private View rootView;

    private final Map<String, String> thumbnails = new HashMap<String, String>() {{
        put("58611129-2dbc-4a81-a72f-77ddfc1b1b49", "https://upload.wikimedia.org/wikipedia/en/0/02/My_Neighbor_Totoro_-_Tonari_no_Totoro_%28Movie_Poster%29.jpg");
    }};

    HomeMovieAdapter(List<Film> films, HomeFragment.FilmItemListener mListener) {
        this.films = films;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_film_home, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Film film = films.get(position);
        String thumbnail = thumbnails.get(film.getId());

        holder.titleTxt.setText(film.getTitle());
        if (thumbnail != null) Glide.with(rootView).load(thumbnail).into(holder.coverImg);
        holder.itemView.setOnClickListener(__ -> mListener.onFilmClick(film));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void updateFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTxt;
        ImageView coverImg;

        ViewHolder(View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.title);
            coverImg = itemView.findViewById(R.id.cover);
        }
    }

}
