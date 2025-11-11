package com.sts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sts.entity.Vehicle;
import com.sts.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    public VehicleController(VehicleService service){ this.vehicleService = service; }

    @PostMapping("/dealer/{dealerId}")
    public ResponseEntity<Vehicle> create(@PathVariable Long dealerId, @RequestBody Vehicle v){
        return ResponseEntity.ok(vehicleService.create(dealerId, v));
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> list(){ return ResponseEntity.ok(vehicleService.list()); }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> get(@PathVariable Long id){ return ResponseEntity.ok(vehicleService.get(id)); }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle v){
        return ResponseEntity.ok(vehicleService.update(id, v));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        vehicleService.delete(id); return ResponseEntity.noContent().build();
    }

    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<Vehicle>> byDealer(@PathVariable Long dealerId){
        return ResponseEntity.ok(vehicleService.findByDealer(dealerId));
    }

    // Requirement: fetch vehicles belonging to PREMIUM dealers only
    @GetMapping("/premium-dealers")
    public ResponseEntity<List<Vehicle>> premiumDealersVehicles(){
        return ResponseEntity.ok(vehicleService.vehiclesOfPremiumDealers());
    }
}

