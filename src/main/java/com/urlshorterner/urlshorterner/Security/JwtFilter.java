package com.urlshorterner.urlshorterner.Security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String header = request.getHeader("Authorization");

                if(header!=null  &&  header.startsWith("Bearer ")){
                    String token = header.substring(7);
                    Claims claim  = jwtUtil.extractAllClaims(token);
                    String email = claim.getSubject();
               

                if(email !=null){

                    UsernamePasswordAuthenticationToken authentication  = new 
                                                          UsernamePasswordAuthenticationToken(email, null,Collections.emptyList());
                                                    

                    SecurityContextHolder.getContext().setAuthentication(authentication);                                
                    
                }
            }

            filterChain.doFilter(request, response);
        
    }



}
