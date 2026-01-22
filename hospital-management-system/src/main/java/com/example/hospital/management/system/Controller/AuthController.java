package com.example.hospital.management.system.Controller;

import com.example.hospital.management.system.Dto.LoginRequestDto;
import com.example.hospital.management.system.Dto.LoginResponceDto;
import com.example.hospital.management.system.Dto.SignUpResponceDto;
import com.example.hospital.management.system.Security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


  @PostMapping("/login")
  public ResponseEntity<LoginResponceDto> login(@RequestBody LoginRequestDto loginRequestDto ){
      return ResponseEntity.ok(authService.login(loginRequestDto));
  }


    @PostMapping("/signup")
   public ResponseEntity<SignUpResponceDto> signUp(@RequestBody LoginRequestDto signUpRequestDto){
      return ResponseEntity.ok(authService.signUp(signUpRequestDto));
}
}
