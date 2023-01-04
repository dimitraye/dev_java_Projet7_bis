package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.IRuleNameService;
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
 * Manage the requests linked to a rulename
 */
//TODO : Ajouter des commentaires pour les annotations.
@Slf4j
@Controller
public class RuleNameController {
    // TODO: Inject RuleName service
    @Autowired
    IRuleNameService ruleNameService;

    /**
     * Return the list page and the list of ruleName with it.
     * @param model
     * @return list page
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
        //TODO : Ajouter des commentaires pour le model et ses méthodes.
        model.addAttribute("ruleNames", ruleNameService.findAll());
        //TODO : Ajouter des commentaires pour les redirections.
        return "ruleName/list";
    }

    /**
     * Send the user to the Add page.
     * @param bid
     * @return the Add page.
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    /**
     * Validate the data of the formular and add the new ruleName into the DB.
     * @param ruleName
     * @param result
     * @param model
     * @return the list page.
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list
        if (!result.hasErrors()) {
            ruleNameService.save(ruleName);
            log.info("The ruleName has been saved");
            //TODO : Ajouter des commentaires pour le model et ses méthodes.
            model.addAttribute("ruleNames", ruleNameService.findAll());
            //TODO : Ajouter des commentaires pour les redirections.
            return "redirect:/ruleName/list";
        }

        //TODO : Ajouter des commentaires pour les redirections.
        return "ruleName/add";
    }

    /**
     *  Send the user to the update page.
     * @param id
     * @param model
     * @return the update page
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form
        RuleName ruleName = ruleNameService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        //TODO : Ajouter des commentaires pour le model et ses méthodes.
        model.addAttribute("ruleName", ruleName);

        //TODO : Ajouter des commentaires pour les redirections.
        return "ruleName/update";
    }

    /**
     * Check the required fields and save the update
     * @param id
     * @param ruleName
     * @param result
     * @param model
     * @return the list page.
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        if (result.hasErrors()) {
            //TODO : Ajouter des commentaires pour les redirections.
            return "ruleName/update";
        }

        ruleName.setId(id);
        ruleNameService.save(ruleName);
        log.info("The ruleName has been saved");
        //TODO : Ajouter des commentaires pour le model et ses méthodes.
        model.addAttribute("ruleNames", ruleNameService.findAll());

        //TODO : Ajouter des commentaires pour les redirections.
        return "redirect:/ruleName/list";
    }

    /**
     * Delete the rating.
     * @param id
     * @param model
     * @return the list page.
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        RuleName ruleName = ruleNameService.findById(id).
            orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        ruleNameService.delete(ruleName);
        log.info("The ruleName has been deleted");
        //TODO : Ajouter des commentaires pour le model et ses méthodes.
        model.addAttribute("ruleNames", ruleNameService.findAll());

        //TODO : Ajouter des commentaires pour les redirections.
        return "redirect:/ruleName/list";
    }
}
