package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
//TODO : Ajouter des commentaires pour les annotations.
@Controller
public class HomeController
{
	/**
	 * Return the home page.
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String home(Model model)
	{
		//TODO : Ajouter des commentaires pour les redirections.
		//Retourne l'endpoint home qui affiche la page home

		return "home";
	}

	/**
	 * Return the bidList page.
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		//Retourne l'endpoint bidList/list qui affiche la page list
		return "redirect:/bidList/list";
	}


}
