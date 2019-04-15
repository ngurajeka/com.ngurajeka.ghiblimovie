package com.ngurajeka.ghiblimovie.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ngurajeka.ghiblimovie.R;
import com.ngurajeka.ghiblimovie.model.Film;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeMovieAdapter extends RecyclerView.Adapter<HomeMovieAdapter.ViewHolder> {

    private List<Film> films;
    private HomeFragment.FilmItemListener mListener;

    HomeMovieAdapter(List<Film> films, HomeFragment.FilmItemListener mListener) {
        this.films = films;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_film_home, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Film film = films.get(position);
        holder.titleTxt.setText(film.getTitle());
        holder.descriptionTxt.setText(film.getDescription());
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
        TextView descriptionTxt;

        ViewHolder(View itemView) {
            super(itemView);

            titleTxt = itemView.findViewById(R.id.title);
            descriptionTxt = itemView.findViewById(R.id.description);
        }
    }

}
