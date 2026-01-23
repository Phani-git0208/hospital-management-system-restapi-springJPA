package com.example.hospital.management.system.Controller;

import com.example.hospital.management.system.Entity.User;
import com.example.hospital.management.system.Entity.type.Role;
import com.example.hospital.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/promote/{userId}")
    public String promoteToDoctor(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setRole(Role.DOCTOR);
        userRepository.save(user);

        return "User promoted to DOCTOR";
    }


}