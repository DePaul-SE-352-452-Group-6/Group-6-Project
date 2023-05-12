package com.group6.project.relational.digitalassets;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/currency")
@Tag(name = "Currency", description = "CRUD operations on currency")
@Log4j2
public class CurrenciesService {
    @Autowired
    private CurrenciesRepository repo;

    @GetMapping
    @Operation(summary = "Returns all the currencies in the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation=Currency.class))})
    public List<Currency> list() {
        log.traceEntry("Enter list");
        var returnVal = repo.findAll();
        log.traceExit("Exit list", returnVal);
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Currency get(@PathVariable("id") Long CurrencyId) {
        log.traceEntry("get", CurrencyId);
        Currency currency = repo.findById(CurrencyId).orElse(new Currency());
        log.traceExit("get", CurrencyId);
        return currency;
    }

    @PutMapping
    public void update(Currency currency) {
        log.traceEntry("update", currency);

        // find the digital good from database
        var repoCurrency = get(currency.getId());

        // update the values for digital good in database to value that was passed
        repoCurrency.setName(currency.getName());
        repoCurrency.setInitialCount(currency.getInitialCount());

        // save the updated value
        repo.save(repoCurrency);
        log.traceExit("update", currency);
    }

    @PostMapping
    @Operation(summary = "Save the currency and returns the currency id")
    public long save(Currency currency) {
        log.traceEntry("enter save", currency);
        repo.save(currency);
        log.traceExit("exit save", currency);
        return currency.getId();
    }


    @PostMapping("/validated")
    @Operation(summary = "Save the currency and returns the currency name")
    public ResponseEntity<String> validatedSave(@Valid @RequestBody Currency currency) {
        log.traceEntry("enter save", currency);
        repo.save(currency);
        log.traceExit("exit save", currency);
        return ResponseEntity.ok("new currency's name is " + currency.getName());
    }

    @DeleteMapping
    @Operation(summary = "Delete the currency")
    public void delete(long id) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
