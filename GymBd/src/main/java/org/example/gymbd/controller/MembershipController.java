package org.example.gymbd.controller;

import org.example.gymbd.model.Membership;
import org.example.gymbd.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memberships")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @PostMapping("/assign/{userId}")
    public ResponseEntity<Membership> assignMembership(@PathVariable Long userId, @RequestParam String type) {
        return new ResponseEntity<>(membershipService.assignMembership(userId, type), HttpStatus.CREATED);
    }
}