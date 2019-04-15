package com.ngurajeka.ghiblimovie.repository;

import com.ngurajeka.ghiblimovie.model.Film;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface FilmApi {

    @GET("films")
    Single<List<Film>> listFilms();

}
