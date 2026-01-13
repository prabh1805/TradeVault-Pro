package com.tradevault.pro.controller;

import com.tradevault.pro.dto.AuthenticationResponse;
import com.tradevault.pro.dto.LoginRequest;
import com.tradevault.pro.dto.RegisterRequest;
import com.tradevault.pro.model.User;
import com.tradevault.pro.security.JwtSecurity;
import com.tradevault.pro.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtSecurity jwtSecurity;

    //Login endpoint
    @PostMapping("/login")
    @NonNull
    public ResponseEntity<@NonNull AuthenticationResponse> login(
            @NonNull @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        assert userDetails != null;
        String jwtToken = jwtSecurity.generateToken(userDetails);

        return ResponseEntity.ok(
                AuthenticationResponse.builder()
                        .token(jwtToken)
                        .email(userDetails.getUsername())
                        .build()
        );
    }

    //Register Endpoint
    @PostMapping("/register")
    public ResponseEntity<@NonNull AuthenticationResponse> register(@NonNull @RequestBody RegisterRequest
                                                                                registerRequest){
        User user = userService.registerUser(
                registerRequest.getUserName(),
                registerRequest.getEmail(),
                registerRequest.getPassword()
        );

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))
                .build();

        String jwtToken = jwtSecurity.generateToken(userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .build());

    }

}
