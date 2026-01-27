package com.example.hospital.management.system.Dto;


import com.example.hospital.management.system.Entity.type.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponceDto {

   private String jwt;
   private Long id;
   private Role role;

};
