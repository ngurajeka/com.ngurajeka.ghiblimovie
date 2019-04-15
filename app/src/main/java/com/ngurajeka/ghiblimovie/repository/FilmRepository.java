package com.ngurajeka.ghiblimovie.repository;

import com.ngurajeka.ghiblimovie.model.Film;

import java.util.List;

import io.reactivex.Single;

public interface FilmRepository {

    Single<List<Film>> getFilms();

}
