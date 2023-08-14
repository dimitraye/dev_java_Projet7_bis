package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.IBidListService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
 * Manage the requests linked to a bidList
 */

//TODO : Ajouter des commentaires pour les annotations.
@Slf4j
@Controller
public class BidListController {
    // TODO: Inject Bid service

    @Autowired
    IBidListService bidListService;

    /**
     * Return the list page and the list of bildList with it.
     * @param model
     * @return list page
     */
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view

        List<BidList> bids = new ArrayList<>(bidListService.findAll());
        //L'objet model de la classe Model permet de transmettre des propriétés à la vue
        //Ajoute les attributs de bids et indique quel sera le nom de l'objet bids dans la vue
        model.addAttribute("bids", bids);

        //Retourne le fichier list.html qui se trouve dans le dossier bidlist pour afficher la page list
        return "bidList/list";
    }

    /**
     * Send the user to the Add page.
     * @param bid
     * @return the Add page.
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        //Retourne le fichier add.html qui se trouve dans le dossier bidlist pour afficher la page add
        return "bidList/add";
    }

    /**
     * Validate the data of the formular and add the new bidList into the DB.
     * @param bid
     * @param result
     * @param model
     * @return the list page.
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if (!result.hasErrors()){
            bidListService.save(bid);
            log.info("The bidList has been saved");
            //Retourne l'endpoint bidlist/list qui execute la méthode home qui permet d'afficher la page list
            return  "redirect:/bidList/list";
        }
        //Retourne le fichier add.html qui se trouve dans le dossier bidlist pour afficher la page list
        return "bidList/add";
    }

    /**
     *  Send the user to the update page.
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        BidList bidList = bidListService.findById(id).get();
        //L'objet model de la classe Model permet de transmettre des propriétés à la vue
        //Ajoute les attributs de bids et indique quel sera le nom de l'objet bidList dans la vue
        model.addAttribute("bidList", bidList);

        //Retourne le fichier update.html qui se trouve dans le dossier bidlist pour afficher la page update
        return "bidList/update";
    }

    /**
     * Check the required fields and save the update
     * @param id
     * @param bidList
     * @param result
     * @param model
     * @return the list page.
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        bidListService.save(bidList);
        log.info("The bidList has been saved");
        //Retourne l'endpoint bidlist/list qui execute la méthode home qui permet d'afficher la page list
        return "redirect:/bidList/list";
    }

    /**
     * Delete the bidlist.
     * @param id
     * @param model
     * @return the list page.
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        bidListService.delete(id);
        log.info("The bidList has been deleted");
        //Retourne l'endpoint bidlist/list qui execute la méthode home qui permet d'afficher la page list
        return "redirect:/bidList/list";
    }
}
