package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.IUserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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


/**
 * Manage the requests linked to a user
 */
//TODO : Ajouter des commentaires pour les annotations.
@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IUserService userService;

    /**
     * Return the list page and the list of user with it.
     * @param model
     * @return list page
     */
    @RequestMapping("/user/list")
    public String home(Model model)
    {

        //TODO : Ajouter des commentaires pour le model et ses méthodes.
        //L'objet model de la classe Model permet de passer des propriétés du model à la vue
        //Ajoute les attributs de bids et indique quel sera le nom, dans la vue, de l'objet qui
        //recupérera les valeurs transmisent par userService.findAll()
        model.addAttribute("users", userService.findAll());
        //Retourne l'endpoint user/list qui affiche la page list
        return "user/list";
    }

    /**
     * Send the user to the Add page.
     * @param bid
     * @return the Add page.
     */
    @GetMapping("/user/add")
    public String addUser(User bid) {
        //Retourne l'endpoint user/add qui affiche la page add
        return "user/add";
    }

    /**
     * Validate the data of the formular and add the new user into the DB.
     * @param user
     * @param result
     * @param model
     * @return the list page.
     */@PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        // check password schema format
        //TODO : Ajouter des commentaires pour cette ligne
        PasswordValidator validator = userService.getValidator();
        //TODO : Ajouter des commentaires pour cette ligne
        RuleResult resultForPassword = validator.validate(new PasswordData(user.getPassword()));
        if (!resultForPassword.isValid()) {
            //TODO : Ajouter des commentaires pour cette ligne
            validator.getMessages(resultForPassword).forEach(errorMessage -> {
                log.error("The password is incorrect");
                //TODO : Ajouter des commentaires pour cette ligne
                result.addError(new FieldError("user", "password", errorMessage));
            });
        }

        if (result.hasErrors()) {
            //Retourne l'endpoint user/add qui affiche la page add
            return "/user/add";
        }

        //TODO : Ajouter des commentaires pour cette ligne
        User userFromDB = userService.findByUserName(user.getUsername()).orElse(null);
        if (userFromDB != null){
            log.error("The username : " + user.getUsername() + " is already registered in the data base");
            //TODO : Ajouter des commentaires pour le model et ses méthodes.
            model.addAttribute("error",
                "The username : " + user.getUsername() + " is already registered in the data base.");
            //Retourne l'endpoint user/add qui affiche la page add
            return "/user/add";
        }

        //TODO : Ajouter des commentaires pour cette ligne
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //TODO : Ajouter des commentaires pour cette ligne
        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
        log.info("The user has been saved");
        //L'objet model de la classe Model permet de passer des propriétés du model à la vue
        //Ajoute les attributs de bids et indique quel sera le nom, dans la vue, de l'objet qui
        //recupérera les valeurs transmisent par userService.findAll()
        model.addAttribute("users", userService.findAll());
        //Retourne l'endpoint user/list qui affiche la page list
        return "redirect:/user/list";
    }

    /**
     *  Send the user to the update page.
     * @param id
     * @param model
     * @return the update page
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        //TODO : Ajouter des commentaires pour cette ligne
        User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        //TODO : Ajouter des commentaires pour cette ligne
        user.setPassword("");
        //L'objet model de la classe Model permet de passer des propriétés du model à la vue
        //Ajoute les attributs de bids et indique quel sera le nom de l'objet user dans la vue
        model.addAttribute("user", user);
        //Retourne l'endpoint user/update qui affiche la page update
        return "user/update";
    }

    /**
     * Check the required fields and save the update
     * @param id
     * @param user
     * @param result
     * @param model
     * @return the list page.
     */@PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        // check password schema format
        //TODO : Ajouter des commentaires pour cette ligne
        PasswordValidator validator = userService.getValidator();
        //TODO : Ajouter des commentaires pour cette ligne
        RuleResult resultForPassword = validator.validate(new PasswordData(user.getPassword()));
        if (!resultForPassword.isValid()) {
            //TODO : Ajouter des commentaires pour cette ligne
            validator.getMessages(resultForPassword).forEach(errorMessage -> {
                log.error("The password is incorrect");
                //TODO : Ajouter des commentaires pour cette ligne
                result.addError(new FieldError("user", "password", errorMessage));
            });
        }

        if (result.hasErrors()) {
            //Retourne l'endpoint user/add qui affiche la page add
            return "/user/add";
        }

        User userFromDB = userService.findByUserName(user.getUsername()).orElse(null);
        if (userFromDB != null && (userFromDB.getId() != id) ){
            log.error("The username : " + user.getUsername() + " is already registered in the data base.");
            //TODO : Ajouter des commentaires pour le model et ses méthodes.
            model.addAttribute("error",
                "The username : " + user.getUsername() + " is already registered in the data base.");
            //Retourne l'endpoint user/add qui affiche la page add
            return "/user/add";
        }

        //TODO : Ajouter des commentaires pour cette ligne
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //TODO : Ajouter des commentaires pour cette ligne
        user.setPassword(encoder.encode(user.getPassword()));
        //TODO : Ajouter des commentaires pour cette ligne
        user.setId(id);
        userService.save(user);
        log.info("The user has been saved");
        //L'objet model de la classe Model permet de passer des propriétés du model à la vue
        //Ajoute les attributs de bids et indique quel sera le nom, dans la vue, de l'objet qui
        //recupérera les valeurs transmisent par userService.findAll()
        model.addAttribute("users", userService.findAll());
        //Retourne l'endpoint user/list qui affiche la page list
        return "redirect:/user/list";
    }

    /**
     * Delete the user.
     * @param id
     * @param model
     * @return the list page.
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        //TODO : Ajouter des commentaires pour cette ligne
        User user = userService.findById(id).
            orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.delete(user);
        log.info("The user has been deleted");
        //L'objet model de la classe Model permet de passer des propriétés du model à la vue
        //Ajoute les attributs de bids et indique quel sera le nom, dans la vue, de l'objet qui
        //recupérera les valeurs transmisent par userService.findAll()
        model.addAttribute("users", userService.findAll());
        //Retourne l'endpoint user/list qui affiche la page list
        return "redirect:/user/list";
    }
}
