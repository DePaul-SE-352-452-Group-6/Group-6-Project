package com.group6.project.relational.leaderboards;
import java.util.*;

import com.group6.project.relational.account.Account;
import com.group6.project.relational.account.AccountRepository;
import com.group6.project.relational.digitalassets.DigitalGood;
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
@Tag(name = "Leaderboard", description = "All of the leaderboard entries created for users")
@Log4j2
public class LeaderboardService {
    @Autowired
    private LeaderboardRepository repo;

    @GetMapping
    @Operation(summary = "Returns all the leaderboard entries in the database")
    @ApiResponse(responseCode = "200", description = "valid response",
            content = {@Content(mediaType="application/json", schema=@Schema(implementation= Leaderboard.class))})
    public List<Leaderboard> list() {
        log.traceEntry("Enter leaderboard");
        var ans = repo.findAll();
        log.traceExit("Exit leaderboard", ans);
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Leaderboard get(@PathVariable("id") long id) {
        log.traceEntry("get", id);
        Leaderboard entry = repo.findById(id);
        log.traceExit("get", id);
        return entry;
    }

    @PostMapping
    @Operation(summary = "Save the leaderboard entry and returns the entry id")
    public void save(@RequestBody Leaderboard Leaderboard) {
        log.traceEntry("enter save", Leaderboard);
        repo.save(Leaderboard);
        log.traceExit("exit save", Leaderboard);
    }

    @DeleteMapping
    @Operation(summary = "Delete the leaderboard entry")
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
