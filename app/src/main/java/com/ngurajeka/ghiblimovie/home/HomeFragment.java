package com.ngurajeka.ghiblimovie.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.ngurajeka.ghiblimovie.R;
import com.ngurajeka.ghiblimovie.model.Film;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.google.common.base.Preconditions.checkNotNull;

@SuppressWarnings("WeakerAccess")
public class HomeFragment extends Fragment implements HomeContract.View {

    private HomeContract.Presenter mPresenter;
    private HomeMovieAdapter movieAdapter;

    // ui
    private View rootView;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieAdapter = new HomeMovieAdapter(new ArrayList<>(0), mItemListener);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.film_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(movieAdapter);

        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMovies(List<Film> films) {
        movieAdapter.updateFilms(films);
    }

    @Override
    public void showAddMovie() {

    }

    @Override
    public void showMovieDetailsUi(String movieId) {

    }

    @Override
    public void showLoadingMoviesError() {
        showMessage("error loading list films");
    }

    @Override
    public void showNoMovies() {

    }

    @Override
    public void showSuccessfullySavedMessage() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showFilteringPopUpMenu() {

    }

    private void showMessage(String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show();
    }

    FilmItemListener mItemListener = new FilmItemListener() {
        @Override
        public void onFilmClick(Film clickedFilm) {
            mPresenter.openMovieDetails(clickedFilm);
        }

    };

    public interface FilmItemListener {

        void onFilmClick(Film clickedFilm);

    }

}
