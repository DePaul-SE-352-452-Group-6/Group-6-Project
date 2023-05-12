package com.group6.project.relational.digitalassets;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts/inventories")
@Tag(name = "Account Inventory", description = "CRUD operations on account inventory")
@Log4j2
public class AccountInventoryService {
    @Autowired
    private AccountInventoryRepository repo;

    @GetMapping
    @Operation(summary = "Returns all the account inventory in the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation=AccountInventory.class))})
    public List<AccountInventory> list() {
        log.traceEntry("Enter list");
        var returnVal = repo.findAll();
        log.traceExit("Exit list", returnVal);
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public AccountInventory get(@PathVariable("id") Long AccountInventoryId) {
        log.traceEntry("get", AccountInventoryId);
        AccountInventory accountInventory = repo.findById(AccountInventoryId).orElse(new AccountInventory());
        log.traceExit("get", AccountInventoryId);
        return accountInventory;
    }

    @PutMapping
    public void update(AccountInventory accountInventory) {
        log.traceEntry("update", accountInventory);

        // find the digital good from database
        var repoAccountInventory = get(accountInventory.getId());

        // update the values for digital good in database to value that was passed
        repoAccountInventory.setAccount(accountInventory.getAccount());
        repoAccountInventory.setDigitalGoodID(accountInventory.getDigitalGoodID());
        repoAccountInventory.setAmount(accountInventory.getAmount());

        // save the updated value
        repo.save(repoAccountInventory);
        log.traceExit("update", accountInventory);
    }

    @PostMapping
    @Operation(summary = "Save the account inventory and returns the account inventory id")
    public long save(AccountInventory accountInventory) {
        log.traceEntry("enter save", accountInventory);
        repo.save(accountInventory);
        log.traceExit("exit save", accountInventory);
        return accountInventory.getId();
    }

    @DeleteMapping
    @Operation(summary = "Delete the account inventory")
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
