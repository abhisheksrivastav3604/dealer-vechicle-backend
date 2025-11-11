package com.sts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sts.entity.Dealer;
import com.sts.service.DealerService;

import java.util.List;

@RestController
@RequestMapping("/api/dealers")
public class DealerController {
    private final DealerService dealerService;
    public DealerController(DealerService dealerService){ this.dealerService = dealerService; }

    @PostMapping
    public ResponseEntity<Dealer> create(@RequestBody Dealer d){ return ResponseEntity.ok(dealerService.create(d)); }

    @GetMapping
    public ResponseEntity<List<Dealer>> list(){ return ResponseEntity.ok(dealerService.list()); }

    @GetMapping("/{id}")
    public ResponseEntity<Dealer> get(@PathVariable Long id){ return ResponseEntity.ok(dealerService.get(id)); }

    @PutMapping("/{id}")
    public ResponseEntity<Dealer> update(@PathVariable Long id, @RequestBody Dealer d){ return ResponseEntity.ok(dealerService.update(id, d)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){ dealerService.deleteDealer(id); 
    return ResponseEntity.ok("Dealer deleted successfully");}
}

