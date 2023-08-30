package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.IRatingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


/**
 * Manage the requests linked to a rating
 */

@AllArgsConstructor
@Slf4j
@Controller
@RequestMapping("/rating")
public class RatingController {
    private final IRatingService ratingService;


    /**
     * Return the list page and the list of rating with it.
     * @param model
     * @return list page
     */
    @RequestMapping("/list")
    public String home(Model model)
    {
        model.addAttribute("ratings", ratingService.findAll());
        return "rating/list";
    }

    /**
     * Send the user to the Add page.
     * @param rating
     * @return the Add page.
     */
    @GetMapping("/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**
     * Validate the data of the form and add the new rating into the DB.
     * @param rating
     * @param result
     * @param model
     * @return the list page.
     */
    @PostMapping("/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ratingService.save(rating);
            log.info("The rating has been saved");
            model.addAttribute("ratings", ratingService.findAll());
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    /**
     *  Send the user to the update page.
     * @param id
     * @param model
     * @return the update page
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    /**
     * Check the required fields and save the update
     * @param id
     * @param rating
     * @param result
     * @param model
     * @return the list page.
     */
    @PostMapping("/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/update";
        }

        rating.setId(id);
        ratingService.save(rating);
        log.info("The rating has been saved");
        model.addAttribute("ratings", ratingService.findAll());

        return "redirect:/rating/list";
    }

    /**
     * Delete the rating.
     * @param id
     * @param model
     * @return the list page.
     */
    @GetMapping("/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.delete(id);
        log.info("The rating has been deleted");
        model.addAttribute("ratings", ratingService.findAll());

        return "redirect:/rating/list";
    }
}
