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
@RequestMapping("/api/digitalGoods")
@Tag(name = "Digital Goods", description = "CRUD operations on digital goods")
@Log4j2
public class DigitalGoodsService {

    @Autowired
    private DigitalGoodsRepository repo;

    @GetMapping
    @Operation(summary = "Returns all the digital goods in the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation=DigitalGood.class))})
    public List<DigitalGood> list() {
        log.traceEntry("Enter list");
        var returnVal = repo.findAll();
        log.traceExit("Exit list", returnVal);
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public DigitalGood get(@PathVariable("id") Long DigitalGoodId) {
        log.traceEntry("get", DigitalGoodId);
        DigitalGood digitalGood = repo.findById(DigitalGoodId).orElse(new DigitalGood());
        log.traceExit("get", DigitalGoodId);
        return digitalGood;
    }

    @PutMapping
    public void update(DigitalGood digitalGood) {
        log.traceEntry("update", digitalGood);

        // find the digital good from database
        var repoDigitalGood = get(digitalGood.getId());

        // update the values for digital good in database to value that was passed
        repoDigitalGood.setName(digitalGood.getName());
        repoDigitalGood.setCosts(digitalGood.getCosts());
        repoDigitalGood.setCurrencyId(digitalGood.getCurrencyId());

        // save the updated value
        repo.save(repoDigitalGood);
        log.traceExit("update", digitalGood);
    }

    @PostMapping
    @Operation(summary = "Save the digital good and returns the digital good id")
    public long save(DigitalGood digitalGood) {
        log.traceEntry("enter save", digitalGood);
        repo.save(digitalGood);
        log.traceExit("exit save", digitalGood);
        return digitalGood.getId();
    }


    @PostMapping("/validated")
    @Operation(summary = "Save the digital goods and returns the digital good name")
    public ResponseEntity<String> validatedSave(@Valid @RequestBody DigitalGood digitalGood) {
        log.traceEntry("enter save", digitalGood);
        repo.save(digitalGood);
        log.traceExit("exit save", digitalGood);
        return ResponseEntity.ok("new digital good's name is " + digitalGood.getName());
    }

    @DeleteMapping
    @Operation(summary = "Delete the digital good")
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
