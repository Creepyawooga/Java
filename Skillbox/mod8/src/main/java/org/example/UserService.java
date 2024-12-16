package org.example;

import org.example.User;
import org.example.UserRepository; // Assuming you have a repository
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;  // Injecting the repository to interact with the database

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);  // Query the repository to find the user
    }
}
