package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.IBidListService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Manage the requests linked to a bidList
 */

@AllArgsConstructor
@Slf4j
@Controller
@RequestMapping("/bidList")
public class BidListController {
    private final IBidListService bidListService;

    /**
     * Return the list page and the list of bildList with it.
     * @param model
     * @return list page
     */
    @RequestMapping("/list")
    public String home(Model model)
    {
        List<BidList> bids = new ArrayList<>(bidListService.findAll());
        model.addAttribute("bids", bids);

        return "bidList/list";
    }

    /**
     * Send the user to the Add page.
     * @param bid
     * @return the Add page.
     */
    @GetMapping("/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
     * Validate the data of the form and add the new bidList into the DB.
     * @param bid
     * @param result
     * @param model
     * @return the list page.
     */
    @PostMapping("/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (!result.hasErrors()){
            bidListService.save(bid);
            log.info("The bidList has been saved");
            return  "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    /**
     *  Send the user to the update page.
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id).get();
        model.addAttribute("bidList", bidList);

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
    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        bidListService.save(bidList);
        log.info("The bidList has been updated");
        return "redirect:/bidList/list";
    }

    /**
     * Delete the bidlist.
     * @param id
     * @param model
     * @return the list page.
     */
    @GetMapping("/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.delete(id);
        log.info("The bidList has been deleted");
        return "redirect:/bidList/list";
    }
}
