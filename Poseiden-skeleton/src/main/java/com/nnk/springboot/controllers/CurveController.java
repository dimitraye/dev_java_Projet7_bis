package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointServiceImpl;
import com.nnk.springboot.services.ICurvePointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Manage the requests linked to a curvePoint
 */

//TODO : Ajouter des commentaires pour les annotations.
@Slf4j
@Controller
public class CurveController {
    // TODO: Inject Curve Point service
    @Autowired
    ICurvePointService  curvePointService;


    /**
     * Return the list page and the list of curvePoint with it.
     * @param model
     * @return list page
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
        //TODO : Ajouter des commentaires pour le model et ses méthodes.
        model.addAttribute("curvePoints", curvePointService.findAll());
        //Retourne l'endpoint curvePoint/list qui affiche la page list
        return "curvePoint/list";
    }

    /**
     * Send the user to the Add page.
     * @param bid
     * @return the Add page.
     */
    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        //Retourne l'endpoint curvePoint/add qui affiche la page add
        return "curvePoint/add";
    }


    /**
     * Validate the data of the formular and add the new curvePoint into the DB.
     * @param curvePoint
     * @param result
     * @param model
     * @return the list page.
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        if (!result.hasErrors()) {
            curvePointService.save(curvePoint);
            log.info("The curvePoint has been saved");
            //L'objet model de la classe Model permet de passer des propriétés du model à la vue
            //Ajoute les attributs de bids et indique quel sera le nom, dans la vue, de l'objet qui
            //recupérera les valeurs transmisent par curvePointService.findAll()
            model.addAttribute("curvePoints", curvePointService.findAll());
            //Retourne l'endpoint curvePoint/list qui affiche la page list
            return "redirect:/curvePoint/list";
        }
        //Retourne l'endpoint curvePoint/add qui affiche la page add
        return "curvePoint/add";
    }


    /**
     *  Send the user to the update page.
     * @param id
     * @param model
     * @return the update page
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        CurvePoint curvePoint = curvePointService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        //L'objet model de la classe Model permet de passer des propriétés du model à la vue
        //Ajoute les attributs de bids et indique quel sera le nom de l'objet curvePoint dans la vue
        model.addAttribute("curvePoint", curvePoint);

        //Retourne l'endpoint curvePoint/update qui affiche la page update
        return "curvePoint/update";
    }

    /**
     * Check the required fields and save the update
     * @param id
     * @param curvePoint
     * @param result
     * @param model
     * @return the list page.
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                            BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        if (result.hasErrors()) {
            //Retourne l'endpoint curvePoint/update qui affiche la page update
            return "curvePoint/update";
        }

        curvePoint.setId(id);
        curvePointService.save(curvePoint);
        log.info("The curvePoint has been saved");
        //L'objet model de la classe Model permet de passer des propriétés du model à la vue
        //Ajoute les attributs de bids et indique quel sera le nom, dans la vue, de l'objet qui
        //recupérera les valeurs transmisent par curvePointService.findAll()
        model.addAttribute("curvePoints", curvePointService.findAll());

        //Retourne l'endpoint curvePoint/list qui affiche la page list
        return "redirect:/curvePoint/list";
    }

    /**
     * Delete the curvePoint.
     * @param id
     * @param model
     * @return the list page.
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        CurvePoint curvePoint = curvePointService.findById(id).
            orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        curvePointService.delete(curvePoint);
        log.info("The curvePoint has been deleted");
        //L'objet model de la classe Model permet de passer des propriétés du model à la vue
        //Ajoute les attributs de bids et indique quel sera le nom, dans la vue, de l'objet qui
        //recupérera les valeurs transmisent par curvePointService.findAll()
        model.addAttribute("curvePoints", curvePointService.findAll());
        //Retourne l'endpoint curvePoint/list qui affiche la page list
        return "redirect:/curvePoint/list";
    }
}
