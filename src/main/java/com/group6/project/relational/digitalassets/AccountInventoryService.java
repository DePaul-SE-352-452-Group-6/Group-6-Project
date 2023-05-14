package com.group6.project.relational.digitalassets;

import com.group6.project.relational.account.AccountRepository;
import com.group6.project.relational.account.*;
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
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private DigitalGoodsRepository digitalRepo;

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
        AccountInventory accountInventory = repo.findById(AccountInventoryId).orElse(null);
        log.traceExit("get", AccountInventoryId);
        return accountInventory;
    }

    /*
    this method can only achieve put method, if you want to insert a new info into it,
    you will get a null pointer exception

    @PutMapping
    public void update(AccountInventoryRequest accountInventoryRequest) {
        log.traceEntry("update", accountInventoryRequest);

        // find the digital good from database
        var repoAccountInventory = get(accountInventoryRequest.getId());
        long accountId = accountInventoryRequest.getAccountId();
        long digitalGoodID = accountInventoryRequest.getDigitalGoodID();
        Account account = accountRepo.findById(accountId);
        DigitalGood digitalGood = digitalRepo.findById(digitalGoodID);

        // update the values for digital good in database to value that was passed
        repoAccountInventory.setAccount(account);
        repoAccountInventory.setDigitalGood(digitalGood);
        repoAccountInventory.setAmount(accountInventoryRequest.getAmount());

        // save the updated value
        repo.save(repoAccountInventory);
        log.traceExit("update", repoAccountInventory);
    }
     */

    //actually, post and get methods exert the same function here, so I just need to put them together
    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.PUT})
    @Operation(summary = "PUT or POST the account inventory and returns the account inventory id")
    public long save(AccountInventoryRequest accountInventoryRequest) {
        log.traceEntry("enter save", accountInventoryRequest);
        long accountId = accountInventoryRequest.getAccountId();
        long digitalGoodID = accountInventoryRequest.getDigitalGoodID();
        Account account = accountRepo.findById(accountId);
        DigitalGood digitalGood = digitalRepo.findById(digitalGoodID);
        AccountInventory accountInventory = new AccountInventory(accountInventoryRequest.getId(),
                account, digitalGood, accountInventoryRequest.getAmount());
        repo.save(accountInventory);
        log.traceExit("exit save", accountInventory);
        return accountInventory.getID();
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
