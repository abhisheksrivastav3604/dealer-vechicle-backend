package com.sts.service;
import org.springframework.stereotype.Service;

import com.sts.entity.Dealer;
import com.sts.entity.Vehicle;
import com.sts.repository.DealerRepository;
import com.sts.repository.VehicleRepository;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final DealerRepository dealerRepository;

    public VehicleService(VehicleRepository vehicleRepository, DealerRepository dealerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.dealerRepository = dealerRepository;
    }

    public Vehicle create(Long dealerId, Vehicle v) {
        Dealer d = dealerRepository.findById(dealerId).orElseThrow();
        v.setDealer(d);
        return vehicleRepository.save(v);
    }

    public Vehicle update(Long id, Vehicle changed) {
        Vehicle existing = vehicleRepository.findById(id).orElseThrow();
        existing.setModel(changed.getModel());
        existing.setPrice(changed.getPrice());
        existing.setStatus(changed.getStatus());
        return vehicleRepository.save(existing);
    }

    public Vehicle get(Long id) { return vehicleRepository.findById(id).orElseThrow(); }
    public List<Vehicle> list() { return vehicleRepository.findAll(); }
    public void delete(Long id) { vehicleRepository.deleteById(id); }

    public List<Vehicle> findByDealer(Long dealerId) { return vehicleRepository.findByDealerId(dealerId); }

    public List<Vehicle> vehiclesOfPremiumDealers() {
        return vehicleRepository.findAllByPremiumDealers();
    }
}

