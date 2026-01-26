package com.example.hospital.management.system.Security;

import com.example.hospital.management.system.Dto.LoginRequestDto;
import com.example.hospital.management.system.Dto.LoginResponceDto;
import com.example.hospital.management.system.Dto.SignUpRequestDto;
import com.example.hospital.management.system.Dto.SignUpResponceDto;
import com.example.hospital.management.system.Entity.User;
import com.example.hospital.management.system.Entity.type.Role;
import com.example.hospital.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponceDto login(LoginRequestDto loginRequestDto){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );
        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken( user);
        return new LoginResponceDto(token,user.getId());

    }

    public SignUpResponceDto signUp(SignUpRequestDto signUpRequestDto) {

        // 1. Check if email already exists
        User user = userRepository.findByEmail(signUpRequestDto.getEmail()).orElse(null);
        if (user != null) {
            throw new IllegalArgumentException("already exists");
        }

        // 2. Create user correctly using Lombok builder
        user = userRepository.save(
                User.builder()
                        .name(signUpRequestDto.getName())
                        .email(signUpRequestDto.getEmail())
                        .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                        .role(userRepository.count() == 0 ? Role.ADMIN : Role.PATIENT)
                        .build()
        );

        // 3. Return response
        return new SignUpResponceDto(user.getId(), user.getEmail());
    }

}
