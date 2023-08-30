package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.ITradeService;
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
 * Manage the requests linked to a trade
 */
@AllArgsConstructor
@Slf4j
@Controller
@RequestMapping("/trade")
public class TradeController {
    private final ITradeService tradeService;

    /**
     * Return the list page and the list of trade with it.
     * @param model
     * @return list page
     */
    @RequestMapping("/list")
    public String home(Model model)
    {
        model.addAttribute("trades", tradeService.findAll());
        return "trade/list";
    }

    /**
     * Send the user to the Add page.
     * @param bid
     * @return the Add page.
     */
    @GetMapping("/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    /**
     * Validate the data of the formular and add the new trade into the DB.
     * @param trade
     * @param result
     * @param model
     * @return the list page.
     */
    @PostMapping("/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.save(trade);
            log.info("The trade has been saved");
            model.addAttribute("trades", tradeService.findAll());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    /**
     *  Send the user to the update page.
     * @param id
     * @param model
     * @return the update page
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);

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
    @PostMapping("/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/update";
        }

        trade.setTradeId(id);
        tradeService.save(trade);
        log.info("The trade has been saved");
        model.addAttribute("trades", tradeService.findAll());

        return "redirect:/trade/list";
    }

    /**
     * Delete the trade.
     * @param id
     * @param model
     * @return the list page.
     */
    @GetMapping("/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.delete(id);
        log.info("The trade has been deleted");
        model.addAttribute("trades", tradeService.findAll());

        return "redirect:/trade/list";
    }
}
