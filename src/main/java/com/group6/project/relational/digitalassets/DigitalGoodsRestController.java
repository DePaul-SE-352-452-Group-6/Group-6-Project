package com.group6.project.relational.digitalassets;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/els/digitalGoods")
@Log4j2
public class DigitalGoodsRestController {

    @Autowired
    private esDigitalGoodService esDigitalGoodService;

    @PostMapping
    public ResponseEntity<DigitalGood> createDigitalGood(@RequestBody DigitalGood digitalGood) {
        try {
            DigitalGood savedDigitalGood = esDigitalGoodService.createDigitalGood(digitalGood);
            return new ResponseEntity<>(savedDigitalGood, HttpStatus.CREATED);
        } catch (IOException e) {
            log.error("Error occurred while creating digital good", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<DigitalGood>> getAllDigitalGoods() {
        try {
            Iterable<DigitalGood> allDigitalGoods = esDigitalGoodService.getAllDigitalGoods();
            return new ResponseEntity<>(allDigitalGoods, HttpStatus.OK);
        } catch (IOException e) {
            log.error("Error occurred while retrieving all digital goods", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DigitalGood> getDigitalGoodById(@PathVariable Long id) {
        try {
            DigitalGood digitalGood = esDigitalGoodService.getDigitalGoodById(id);
            return ResponseEntity.ok(digitalGood);
        } catch (IOException e) {
            log.error("Error occurred while retrieving digital good by id", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DigitalGood> updateDigitalGood(@PathVariable Long id, @RequestBody DigitalGood digitalGood) {
        try {
            DigitalGood updatedDigitalGood = esDigitalGoodService.updateDigitalGood(id, digitalGood);
            return ResponseEntity.ok(updatedDigitalGood);
        } catch (IOException e) {
            log.error("Error occurred while updating digital good", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDigitalGood(@PathVariable Long id) {
        try {
            boolean deleted = esDigitalGoodService.deleteDigitalGood(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            log.error("Error occurred while deleting digital good", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/currency/{currencyId}")
    public ResponseEntity<List<DigitalGood>> getDigitalGoodsByCurrency(@PathVariable Integer currencyId) {
        try {
            List<DigitalGood> digitalGoods = esDigitalGoodService.getDigitalGoodsByCurrency(currencyId);
            if (digitalGoods.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(digitalGoods);
            }
        } catch (IOException e) {
            log.error("Error occurred while retrieving digital goods by currency", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/searchByCostRange")
    public ResponseEntity<List<DigitalGood>> searchDigitalGoodsByCostRange(
            @RequestParam Integer minCost, @RequestParam Integer maxCost) {
        try {
            List<DigitalGood> digitalGoods = esDigitalGoodService.searchDigitalGoodsByCostRange(minCost, maxCost);
            return ResponseEntity.ok(digitalGoods);
        } catch (IOException e) {
            log.error("Error occurred while searching digital goods by cost range", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<DigitalGood>> searchDigitalGoodsByName(@RequestParam String name) {
        try {
            List<DigitalGood> digitalGoods = esDigitalGoodService.searchDigitalGoodsByName(name);
            return ResponseEntity.ok(digitalGoods);
        } catch (IOException e) {
            log.error("Error occurred while searching digital goods by name", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

