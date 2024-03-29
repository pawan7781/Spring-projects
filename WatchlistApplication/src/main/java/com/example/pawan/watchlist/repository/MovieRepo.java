package com.example.pawan.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pawan.watchlist.entity.Movie;


@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

}

