package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;

import java.util.List;

public interface RatingService {

    Rating save(Rating rating);

    List<Rating> findAll();

    void delete(Integer id);

    Rating findById(Integer id);

}
