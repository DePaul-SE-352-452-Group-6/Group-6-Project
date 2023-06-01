package com.group6.project.relational.digitalassets;

import com.group6.project.relational.account.AccountCurrency;
import com.group6.project.relational.account.AccountCurrencyRepository;
import jakarta.servlet.http.HttpSession;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/currency")
@Log4j2
public class CurrenciesController {
    @Autowired
    private CurrenciesRepository repo;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("currencies", repo.findAll());
        if (session.getAttribute("currency") == null) {
            model.addAttribute("currency", new Currency());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("currency", session.getAttribute("currency"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "digitalassets/CurrencyList";
    }

    @GetMapping("/edit/{id}")
    public String get(@PathVariable("id") Long id, Model model, HttpSession session) {
        session.setAttribute("currency", repo.findById(id));
        return "redirect:/currency";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/currency";
    }

    @PostMapping
    public String save(@ModelAttribute Currency currency, HttpSession session) {
        if (currency.getId() == 0)
            repo.save(currency);
        else {
            var editCurrency = repo.findById(currency.getId());
            editCurrency.setName(currency.getName());
            editCurrency.setInitialCount(currency.getInitialCount());
            repo.save(editCurrency);
            session.setAttribute("currency", null);
        }
        return "redirect:/currency";
    }

    //    @PostMapping
    public String validatedSave(@ModelAttribute Currency currency) {
        if (currency.getId() == 0)
            repo.save(currency);
        else {
            var editCurrency = repo.findById(currency.getId());
            editCurrency.setName(currency.getName());
            editCurrency.setInitialCount(currency.getInitialCount());
            repo.save(editCurrency);
        }
        return "currency/edit";
    }

    /**
     * When your operation may involve cascading relationships or errors, we will directly return to the page
     * to inform the user that we cannot make changes, instead of directly displaying 500 messages.
     * For example, when a certain currencyId that you want to delete involves or has a cascading relationship with accountCurrency,
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
