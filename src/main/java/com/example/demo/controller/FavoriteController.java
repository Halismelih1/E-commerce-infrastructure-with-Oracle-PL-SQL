package com.example.demo.controller;

import com.example.demo.dto.favourites.AddFav;
import com.example.demo.dto.favourites.MostFavouriteProducts;
import com.example.demo.dto.favourites.RemoveFav;
import com.example.demo.dto.favourites.UserFavouriteList;
import com.example.demo.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavoriteController {
    @Autowired
    FavouriteService favouriteService;

    @PostMapping("/addToFav")
    public ResponseEntity<String> addToFav(@RequestBody AddFav addFav) {
        try {
            favouriteService.callAddToFavouriteProcedure(addFav.getUserId(),addFav.getProductId());
            return new ResponseEntity<>("Add to fav successfully ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed Add to fav: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/removeFromFav")
    public ResponseEntity<String> removeFromFav(@RequestBody RemoveFav removeFav) {
        try {
            favouriteService.callRemoveToFavouriteProcedure(removeFav.getUserId(),removeFav.getProductId());
            return new ResponseEntity<>("Remove From fav successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed remove from fav: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/viewUserFav/{userId}")
    public List<UserFavouriteList> viewUserFav(@PathVariable int userId) {
        return favouriteService.callViewUserFavouritesProcedure(userId);
    }

    @GetMapping("/mostFavouritedProducts")
    public List<MostFavouriteProducts> getMostFavouritedProducts() {
        return favouriteService.callMostFavouritesProcedure();
    }

}
