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
        //TODO : Ajouter des commentaires pour le model et ses méthodes.
        model.addAttribute("trades", tradeService.findAll());
        //TODO : Ajouter des commentaires pour les redirections.
        return "trade/list";
    }

    /**
     * Send the user to the Add page.
     * @param bid
     * @return the Add page.
     */
    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        //TODO : Ajouter des commentaires pour les redirections.
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
            //TODO : Ajouter des commentaires pour le model et ses méthodes.
            model.addAttribute("trades", tradeService.findAll());
            //TODO : Ajouter des commentaires pour les redirections.
            return "redirect:/trade/list";
        }

        //TODO : Ajouter des commentaires pour les redirections.
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
        //TODO : Ajouter des commentaires pour le model et ses méthodes.
        model.addAttribute("trade", trade);

        //TODO : Ajouter des commentaires pour les redirections.
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
            //TODO : Ajouter des commentaires pour les redirections.
            return "trade/update";
        }

        trade.setTradeId(id);
        tradeService.save(trade);
        log.info("The trade has been saved");
        //TODO : Ajouter des commentaires pour le model et ses méthodes.
        model.addAttribute("trades", tradeService.findAll());

        //TODO : Ajouter des commentaires pour les redirections.
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
        //TODO : Ajouter des commentaires pour le model et ses méthodes.
        model.addAttribute("trades", tradeService.findAll());

        //TODO : Ajouter des commentaires pour les redirections.
        return "redirect:/trade/list";
    }
}
