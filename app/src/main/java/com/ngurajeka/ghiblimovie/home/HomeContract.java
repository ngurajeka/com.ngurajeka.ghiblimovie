package com.ngurajeka.ghiblimovie.home;

import com.ngurajeka.ghiblimovie.base.BasePresenter;
import com.ngurajeka.ghiblimovie.base.BaseView;
import com.ngurajeka.ghiblimovie.model.Film;

import java.util.List;

import androidx.annotation.NonNull;

public interface HomeContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMovies(List<Film> films);

        void showAddMovie();

        void showMovieDetailsUi(String movieId);

        void showLoadingMoviesError();

        void showNoMovies();

        void showSuccessfullySavedMessage();

        boolean isActive();

        void showFilteringPopUpMenu();

    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadMovies(boolean forceUpdate);

        void addNewMovie();

        void openMovieDetails(@NonNull Film requestedFilm);

    }

}
