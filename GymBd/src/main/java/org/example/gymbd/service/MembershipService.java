package org.example.gymbd.service;

import org.example.gymbd.model.Membership;
import org.example.gymbd.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    public Membership assignMembership(Long userId, String type) {
        Membership membership = new Membership();
        membership.setId(userId);
        membership.setType(type);
        membership.setStartDate(LocalDate.now());

        switch (type.toUpperCase()) {
            case "BASIC":
                membership.setEndDate(LocalDate.now().plusMonths(3));
                break;
            case "PLATINUM":
                membership.setEndDate(LocalDate.now().plusMonths(6));
                break;
            case "GOLD":
                membership.setEndDate(LocalDate.now().plusYears(1));
                break;
        }

        membership.setActive(true);
        return membershipRepository.save(membership);
    }
}