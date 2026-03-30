package com.urlshorterner.urlshorterner.Controller;


import org.springframework.web.bind.annotation.RestController;

import com.urlshorterner.urlshorterner.DT0.APIResponse;
import com.urlshorterner.urlshorterner.DT0.LoginRequest;
import com.urlshorterner.urlshorterner.DT0.RegisterRequest;
import com.urlshorterner.urlshorterner.Service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<String>> register(@RequestBody RegisterRequest request
    ) {
        String s = userService.register(request);
        
        return ResponseEntity.ok(new APIResponse<String>(true, "successful", s));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> login(@RequestBody LoginRequest request){

         String s = userService.login(request);  
        return ResponseEntity.ok(new APIResponse<String>(true, "successful", s));
    }
    
    

}
