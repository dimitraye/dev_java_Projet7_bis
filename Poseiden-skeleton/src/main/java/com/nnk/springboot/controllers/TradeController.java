package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.ITradeService;
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
 * Manage the requests linked to a trade
 */
//TODO : Ajouter des commentaires pour les annotations.
@Slf4j
@Controller
public class TradeController {
    // TODO: Inject Trade service
    @Autowired
    ITradeService tradeService;

    /**
     * Return the list page and the list of trade with it.
     * @param model
     * @return list page
     */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        // TODO: find all Trade, add to model
        //L'objet model de la classe Model permet de passer des propriétés du model à la vue
        //Ajoute les attributs de bids et indique quel sera le nom, dans la vue, de l'objet qui
        //recupérera les valeurs transmisent par tradeService.findAll()
        model.addAttribute("trades", tradeService.findAll());
        //Retourne l'endpoint trade/list qui affiche la page list
        return "trade/list";
    }

    /**
     * Send the user to the Add page.
     * @param bid
     * @return the Add page.
     */
    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        //Retourne l'endpoint trade/add qui affiche la page add
        return "trade/add";
    }

    /**
     * Validate the data of the formular and add the new trade into the DB.
     * @param trade
     * @param result
     * @param model
     * @return the list page.
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list
        if (!result.hasErrors()) {
            tradeService.save(trade);
            log.info("The trade has been saved");
            //L'objet model de la classe Model permet de passer des propriétés du model à la vue
            //Ajoute les attributs de bids et indique quel sera le nom, dans la vue, de l'objet qui
            //recupérera les valeurs transmisent par tradeService.findAll()
            model.addAttribute("trades", tradeService.findAll());
            //Retourne l'endpoint trade/list qui affiche la page list
            return "redirect:/trade/list";
        }

        //Retourne l'endpoint trade/add qui affiche la page add
        return "trade/add";
    }

    /**
     *  Send the user to the update page.
     * @param id
     * @param model
     * @return the update page
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
        Trade trade = tradeService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        //L'objet model de la classe Model permet de passer des propriétés du model à la vue
        //Ajoute les attributs de bids et indique quel sera le nom de l'objet trade dans la vue
        model.addAttribute("trade", trade);

        //Retourne l'endpoint trade/update qui affiche la page update
        return "trade/update";
    }

    /**
     * Check the required fields and save the update
     * @param id
     * @param trade
     * @param result
     * @param model
     * @return the list page.
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        if (result.hasErrors()) {
            //Retourne l'endpoint trade/update qui affiche la page update
            return "trade/update";
        }

        trade.setTradeId(id);
        tradeService.save(trade);
        log.info("The trade has been saved");
        //TODO : Ajouter des commentaires pour le model et ses méthodes.
        //L'objet model de la classe Model permet de passer des propriétés du model à la vue
        //Ajoute les attributs de bids et indique quel sera le nom, dans la vue, de l'objet qui
        //recupérera les valeurs transmisent par tradeService.findAll()
        model.addAttribute("trades", tradeService.findAll());

        //Retourne l'endpoint trade/list qui affiche la page list
        return "redirect:/trade/list";
    }

    /**
     * Delete the trade.
     * @param id
     * @param model
     * @return the list page.
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        Trade trade = tradeService.findById(id).
            orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        tradeService.delete(trade);
        log.info("The trade has been deleted");
        //L'objet model de la classe Model permet de passer des propriétés du model à la vue
        //Ajoute les attributs de bids et indique quel sera le nom, dans la vue, de l'objet qui
        //recupérera les valeurs transmisent par tradeService.findAll()
        model.addAttribute("trades", tradeService.findAll());

        //Retourne l'endpoint trade/list qui affiche la page list
        return "redirect:/trade/list";
    }
}
