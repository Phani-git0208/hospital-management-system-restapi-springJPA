package com.example.hospital.management.system.Security;

import com.example.hospital.management.system.Dto.LoginRequestDto;
import com.example.hospital.management.system.Dto.LoginResponceDto;
import com.example.hospital.management.system.Dto.SignUpResponceDto;
import com.example.hospital.management.system.Entity.User;
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
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );
        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken( user);
        return new LoginResponceDto(token,user.getId());

    }

    public SignUpResponceDto signUp(LoginRequestDto signUpRequestDto){
        User user =  userRepository.findByUsername(signUpRequestDto.getUsername()).orElse(null);
        if(user != null)throw new IllegalArgumentException("already exists");
        user = userRepository.save(user.builder()
                .username(signUpRequestDto.getUsername())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .build());

        return new SignUpResponceDto(user.getId(), user.getUsername());
    }
}
