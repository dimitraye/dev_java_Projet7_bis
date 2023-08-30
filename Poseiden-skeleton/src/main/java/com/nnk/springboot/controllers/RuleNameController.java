package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.IRuleNameService;
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
 * Manage the requests linked to a rulename
 */
@AllArgsConstructor
@Slf4j
@Controller
@RequestMapping("/ruleName")
public class RuleNameController {
    private final IRuleNameService ruleNameService;

    /**
     * Return the list page and the list of ruleName with it.
     * @param model
     * @return list page
     */
    @RequestMapping("/list")
    public String home(Model model)
    {
        model.addAttribute("ruleNames", ruleNameService.findAll());
        return "ruleName/list";
    }

    /**
     * Send the user to the Add page.
     * @param bid
     * @return the Add page.
     */
    @GetMapping("/add")
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
    @PostMapping("/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameService.save(ruleName);
            log.info("The ruleName has been saved");
            model.addAttribute("ruleNames", ruleNameService.findAll());
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    /**
     *  Send the user to the update page.
     * @param id
     * @param model
     * @return the update page
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        model.addAttribute("ruleName", ruleName);

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
    @PostMapping("/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/update";
        }

        ruleName.setId(id);
        ruleNameService.save(ruleName);
        log.info("The ruleName has been saved");
        model.addAttribute("ruleNames", ruleNameService.findAll());

        return "redirect:/ruleName/list";
    }

    /**
     * Delete the rating.
     * @param id
     * @param model
     * @return the list page.
     */
    @GetMapping("/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.delete(id);
        log.info("The ruleName has been deleted");
        model.addAttribute("ruleNames", ruleNameService.findAll());

        return "redirect:/ruleName/list";
    }
}
