package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController {
    // TODO: Inject Rating service

    private static Logger log = LoggerFactory.getLogger(RatingController.class);

    private RatingService ratingService;

    public RatingController(RatingService rating) {
        ratingService = rating;
    }

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        // TODO: find all Rating, add to model
        log.info("GET request to /rating/list");

        model.addAttribute("ratingList", ratingService.findAll());

        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        log.info("GET Request to /rating/add");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Rating list
        log.info("POST request to /rating/add");

        if(!result.hasErrors()){
            ratingService.save(rating);
        } else {
            return "rating/add";
        }

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Rating by Id and to model then show to the form
        log.info("GET request to /rating/update" +id);

        Rating rating= ratingService.findById(id);
        if(rating != null){
            model.addAttribute("rating",rating);
        }

        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        log.info("POST request to /rating/update" +id);

        if(!result.hasErrors()){
            ratingService.save(rating);
        } else {
            return "rating/update";
        }

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        log.info("GET request to /rating/delete" +id);

        ratingService.delete(id);

        return "redirect:/rating/list";
    }
}
