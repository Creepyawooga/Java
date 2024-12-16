package org.example;

import org.example.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> (GrantedAuthority) () -> role)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Logic to handle expiration
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Logic to handle locked account
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Logic to handle expired credentials
    }

    @Override
    public boolean isEnabled() {
        return true;  // Logic to handle enabled account
    }

    public User getUser() {
        return user;
    }
}
