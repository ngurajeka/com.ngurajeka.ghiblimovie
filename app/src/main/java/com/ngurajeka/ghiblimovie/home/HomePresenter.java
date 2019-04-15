package com.ngurajeka.ghiblimovie.home;


import android.util.Log;

import com.ngurajeka.ghiblimovie.model.Film;
import com.ngurajeka.ghiblimovie.repository.FilmRepository;
import com.ngurajeka.ghiblimovie.repository.FilmRepositoryImpl;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings("WeakerAccess")
public class HomePresenter implements HomeContract.Presenter {

    private FilmRepository filmRepository;
    private HomeContract.View mHomeView;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    public HomePresenter(HomeContract.View homeView) {
        mHomeView = homeView;
        mCompositeDisposable = new CompositeDisposable();
        filmRepository = new FilmRepositoryImpl();

        mHomeView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadMovies(boolean forceUpdate) {
        mHomeView.setLoadingIndicator(true);

        mCompositeDisposable.clear();
        Disposable disposable = filmRepository.getFilms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Film>>() {
                    @Override
                    public void onSuccess(List<Film> films) {
                        Log.d("films size", String.valueOf(films.size()));
                        mHomeView.showMovies(films);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage() != null) Log.d("error", e.getMessage());
                        e.printStackTrace();
                        mHomeView.showLoadingMoviesError();
                    }
                });

        mCompositeDisposable.add(disposable);
    }

    @Override
    public void addNewMovie() {

    }

    @Override
    public void openMovieDetails(@NonNull Film requestedFilm) {

    }

    @Override
    public void subscribe() {
        loadMovies(false);
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
