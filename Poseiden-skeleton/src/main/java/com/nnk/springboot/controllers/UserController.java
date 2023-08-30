package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


/**
 * Manage the requests linked to a user
 */
@AllArgsConstructor
@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    private final IUserService userService;

    /**
     * Return the list page and the list of user with it.
     * @param model
     * @return list page
     */
    @RequestMapping("/list")
    public String home(Model model)
    {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    /**
     * Send the user to the Add page.
     * @param bid
     * @return the Add page.
     */
    @GetMapping("/add")
    public String addUser(User bid) {
        return "user/add";
    }

    /**
     * Validate the data of the formular and add the new user into the DB.
     * @param user
     * @param result
     * @param model
     * @return the list page.
     */@PostMapping("/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {

         result = userService.getBindingResultsErrors(user, result);
        if (result.hasErrors()) {
            return "/user/add";
        }

        User userFromDB = userService.findByUserName(user.getUsername()).orElse(null);
        if (userFromDB != null){
            log.error("The username : " + user.getUsername() + " is already registered in the data base");
            model.addAttribute("error",
                "The username : " + user.getUsername() + " is already registered in the data base.");
            return "/user/add";
        }

        userService.save(user);
        log.info("The user has been saved");

        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    /**
     *  Send the user to the update page.
     * @param id
     * @param model
     * @return the update page
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");

        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Check the required fields and save the update
     * @param id
     * @param user
     * @param result
     * @param model
     * @return the list page.
     */@PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {

        result = userService.getBindingResultsErrors(user, result);
        if (result.hasErrors()) {
            return "/user/update";
        }

        User userFromDB = userService.findByUserName(user.getUsername()).orElse(null);
        if (userFromDB != null && (userFromDB.getId() != id) ){
            log.error("The username : " + user.getUsername() + " is already registered in the data base.");
            model.addAttribute("error",
                "The username : " + user.getUsername() + " is already registered in the data base.");
            return "/user/update";
        }

        userService.update(user, id);
        log.info("The user has been saved");
        model.addAttribute("users", userService.findAll());

        return "redirect:/user/list";
    }

    /**
     * Delete the user.
     * @param id
     * @param model
     * @return the list page.
     */
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.deleteById(id);
        log.info("The user has been deleted");
        model.addAttribute("users", userService.findAll());

        return "redirect:/user/list";
    }
}
