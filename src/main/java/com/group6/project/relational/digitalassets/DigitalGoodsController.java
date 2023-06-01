package com.group6.project.relational.digitalassets;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/digitalGood")
@Log4j2
public class DigitalGoodsController {
    @Autowired
    private DigitalGoodsRepository repo;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("digitalGoods", repo.findAll());
        if (session.getAttribute("digitalGood") == null) {
            model.addAttribute("digitalGood", new DigitalGood());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("digitalGood", session.getAttribute("digitalGood"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "digitalassets/DigitalGoodList";
    }

    @GetMapping("/edit/{id}")
    public String get(@PathVariable("id") Long id, Model model, HttpSession session) {
        session.setAttribute("digitalGood", repo.findById(id));
        return "redirect:/digitalGood";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/digitalGood";
    }

    @PostMapping
    public String save(@ModelAttribute DigitalGood digitalGood, HttpSession session) {
        if (digitalGood.getId() == 0)
            repo.save(digitalGood);
        else {
            var editDigitalGood = repo.findById(digitalGood.getId());
            editDigitalGood.setName(digitalGood.getName());
            editDigitalGood.setCosts(digitalGood.getCosts());
            editDigitalGood.setCurrencyId(digitalGood.getCurrencyId());
            repo.save(editDigitalGood);
            session.setAttribute("digitalGood", null);
        }
        return "redirect:/digitalGood";
    }

    //    @PostMapping
    public String validatedSave(@ModelAttribute DigitalGood digitalGood) {
        if (digitalGood.getId() == 0)
            repo.save(digitalGood);
        else {
            var editDigitalGood = repo.findById(digitalGood.getId());
            editDigitalGood.setName(digitalGood.getName());
            editDigitalGood.setCosts(digitalGood.getCosts());
            editDigitalGood.setCurrencyId(digitalGood.getCurrencyId());
            repo.save(editDigitalGood);
        }
        return "digitalGood/edit";
    }

    /**
     * When your operation may involve cascading relationships or errors, we will directly return to the page
     * to inform the user that we cannot make changes, instead of directly displaying 500 messages.
     * For example, when a certain DigitalGoodId that you want to delete involves or has a cascading relationship with accountInventory,
     * it is not allowed to delete it according to normal principles
     *
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInternalServerError(Exception ex) {
        // Create a custom response for the 500 error
        String errorMessage = "Your operation may involve cascading relationships or errors, so cannot be modified";
        log.error(ex);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
