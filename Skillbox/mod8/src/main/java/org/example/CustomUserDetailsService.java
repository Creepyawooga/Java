package org.example;

import org.example.User;
import org.example.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Using the UserService to find the user by username
        return userService.findByUsername(username)
                .map(CustomUserDetails::new)  // Wrap the found user in a CustomUserDetails object
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
