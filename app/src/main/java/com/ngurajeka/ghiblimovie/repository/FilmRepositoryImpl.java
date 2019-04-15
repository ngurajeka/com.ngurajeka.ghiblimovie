package com.ngurajeka.ghiblimovie.repository;

import com.ngurajeka.ghiblimovie.model.Film;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmRepositoryImpl implements FilmRepository {

    private FilmApi filmApi;

    public FilmRepositoryImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ghibliapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        filmApi = retrofit.create(FilmApi.class);
    }

    @Override
    public Single<List<Film>> getFilms() {
        return filmApi.listFilms();
    }

}
