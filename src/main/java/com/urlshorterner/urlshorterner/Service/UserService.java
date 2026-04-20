package com.urlshorterner.urlshorterner.Service;

import com.urlshorterner.urlshorterner.Repository.urlRepository;
import com.urlshorterner.urlshorterner.Security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.urlshorterner.urlshorterner.DT0.LoginRequest;
import com.urlshorterner.urlshorterner.DT0.RegisterRequest;
import com.urlshorterner.urlshorterner.Entity.User;
import com.urlshorterner.urlshorterner.Repository.UserRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

  
    private final  UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


   public String register(RegisterRequest request)
   {

         System.out.println("Here");
       
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
             throw new RuntimeException("Already Registered , Login now");
        }

        User user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
       
            userRepository.save(user);
     return "successfully Registered" ;

   }

   public String login(LoginRequest request ){

    User user  = userRepository.findByEmail(request.getUsername())
                   .orElseThrow(()-> new RuntimeException("User not found "));
       
   if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){

       throw new RuntimeException("Password is incorrect");
      
   }     
   return jwtUtil.generateToken(user);          



   }

}
