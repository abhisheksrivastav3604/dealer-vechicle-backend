package com.sts.controller;

import com.sts.config.JwtUtil;
import com.sts.entity.Dealer;
import com.sts.entity.enums.SubscriptionType;
import com.sts.repository.DealerRepository;
import com.sts.service.AuthService;
import com.sts.service.DealerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Dealer dealer) {
        dealer.setPassword(passwordEncoder.encode(dealer.getPassword()));
        dealerRepository.save(dealer);
        return ResponseEntity.ok("Dealer registered successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("username");
        String password = request.get("password");

        Dealer dealer = authService.login(email, password);
        System.out.println(dealer);

        String token = jwtUtil.generateToken(dealer.getEmail());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "dealer", dealer
        ));
    }
}
