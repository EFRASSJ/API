package org.example.gymbd.service;

import org.example.gymbd.model.User;
import org.example.gymbd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        user.setActive(true);
        user.setRole("USER");
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getActiveUsers() {
        return userRepository.findAll().stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
