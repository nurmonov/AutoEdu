package org.example.autoedu.service;

import lombok.RequiredArgsConstructor;
import org.example.autoedu.dto.login.LoginRequest;
import org.example.autoedu.dto.login.LoginResponse;
import org.example.autoedu.entity.User;
import org.example.autoedu.filter.JwtUtil;
import org.example.autoedu.repo.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {


        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() -> new UsernameNotFoundException("Telefon raqami topilmadi"));


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Parol noto'g'ri");
        }


        UserDetails userDetails =  new org.springframework.security.core.userdetails.User(
                user.getPhoneNumber(),
                user.getPassword(),
                user.getAuthorities()
        );


        String jwt = jwtUtil.generateToken(userDetails);


        LoginResponse bearer = LoginResponse.builder()
                .token(jwt)
                .tokenType("Bearer")
                .username(user.getPhoneNumber())
                .authorities(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .build();

        System.out.println("Response: " + bearer);
        return bearer;
    }
}