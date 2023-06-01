package com.group6.project.relational.digitalassets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/noaccountinventory")
@Tag(name = "NoAccountInventory", description = "Everything about your Account Inventory")
@Log4j2
public class NoAccountInventoryService {
    @Autowired
    private NoAccountInventoryRepository repo;

    @GetMapping
    @Operation(summary = "Returns all the account inventories")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation = NoAccountInventory.class))})
    public List<NoAccountInventory> list() {
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return retval;
    }

    @PostMapping
    @Operation(summary = "Save the account inventory and returns the ID")
    public String save(NoAccountInventory instructor) {
        log.traceEntry("enter save", instructor);
        repo.save(instructor);
        log.traceExit("exit save", instructor);
        return instructor.getId();
    }

    @DeleteMapping
    @Operation(summary = "Remove the account inventory")
    public void remove(String id) {
        log.traceEntry("Enter remove", id);
        repo.deleteById(id);
        log.traceExit("Exit remove");
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
