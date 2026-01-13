package com.tradevault.pro.service;

import com.tradevault.pro.model.User;
import com.tradevault.pro.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found : " + email));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .authorities(mapRolesToAuthorities(user.getRoles()))
                .build();
    }

    //Register new user
    public User registerUser(String userName, String email, String password){
        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Email already exists");
        }
        User user = User.builder()
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .email(email)
                .roles(List.of("USER"))
                .createdAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    //Return for admin
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // Convert roles → authorities
    private List<SimpleGrantedAuthority> mapRolesToAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


}
