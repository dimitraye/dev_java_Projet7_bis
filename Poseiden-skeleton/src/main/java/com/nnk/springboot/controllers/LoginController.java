package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 */
//TODO : Ajouter des commentaires pour les annotations.
@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    IUserService userService;



    /**
     * Return the login page.
     * @param model
     * @return
     */
    @GetMapping(value = {"/login", "app/login"})
    public String login(Model model, RedirectAttributes redirectAttrs) {
        //Récupère le user courrant et cérifie si'il est authentifié
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            log.info("You are connected");
            //Retourne l'endpoint bidList/list qui affiche la page list
            return "redirect:/bidList/list";
        }
        //Retourne l'endpoint login qui affiche la page login
        return "login";
    }


    /**
     *
     * @return
     */
    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    /**
     *
     * @return
     */
    @GetMapping("/app/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }
}
