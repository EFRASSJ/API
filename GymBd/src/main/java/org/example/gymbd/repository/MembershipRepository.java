package org.example.gymbd.repository;

import org.example.gymbd.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Optional<Membership> findByUserId(Long userId);
}