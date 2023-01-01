package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.IUserService;
import java.util.List;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;

    @RequestMapping("/user/list")
    public String home(Model model)
    {

        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        // check password schema format
        PasswordValidator validator = userService.getValidator();
        RuleResult resultForPassword = validator.validate(new PasswordData(user.getPassword()));
        if (!resultForPassword.isValid()) {
            validator.getMessages(resultForPassword).forEach(errorMessage -> {
                result.addError(new FieldError("user", "password", errorMessage));
            });
        }

        if (result.hasErrors()) {
            return "/user/add";
        }

        User userFromDB = userService.findByUserName(user.getUsername()).orElse(null);
        if (userFromDB != null){
            model.addAttribute("error",
                "The username : " + user.getUsername() + " is already registered in the data base.");
            return "/user/add";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        // check password schema format
        PasswordValidator validator = userService.getValidator();
        RuleResult resultForPassword = validator.validate(new PasswordData(user.getPassword()));
        if (!resultForPassword.isValid()) {
            validator.getMessages(resultForPassword).forEach(errorMessage -> {
                result.addError(new FieldError("user", "password", errorMessage));
            });
        }

        if (result.hasErrors()) {
            return "/user/add";
        }

        User userFromDB = userService.findByUserName(user.getUsername()).orElse(null);
        if (userFromDB != null && (userFromDB.getId() != id) ){
            model.addAttribute("error",
                "The username : " + user.getUsername() + " is already registered in the data base.");
            return "/user/add";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id).
            orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.delete(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }
}
