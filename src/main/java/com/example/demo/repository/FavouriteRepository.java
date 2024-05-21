package com.example.demo.repository;

import com.example.demo.entities.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteRepository extends JpaRepository<Favourites,Integer> {
}
