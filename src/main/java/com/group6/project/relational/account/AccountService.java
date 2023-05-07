package com.group6.project.relational.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account", description = "All of the accounts created for this game")
@Log4j2
public class AccountService {
    @Autowired
    private AccountRepository repo;

    @GetMapping
    @Operation(summary = "Returns all the account in the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation=Account.class))})
    public List<Account> list() {
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return repo.findAll();
    }

    @PostMapping
    @Operation(summary = "Save the account and returns the account id")
    public void save(@RequestBody Account Account) {
        log.traceEntry("enter save", Account);
        repo.save(Account);
        log.traceExit("exit save", Account);
    }


    @DeleteMapping
    @Operation(summary = "Delete the account")
    public void delete(long code) {
        log.traceEntry("Enter delete", code);
        repo.deleteById(code);
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