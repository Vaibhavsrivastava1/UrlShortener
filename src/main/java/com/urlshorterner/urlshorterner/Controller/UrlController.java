package com.urlshorterner.urlshorterner.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.urlshorterner.urlshorterner.DT0.APIResponse;
import com.urlshorterner.urlshorterner.DT0.UrlRequest;
import com.urlshorterner.urlshorterner.Service.UrlService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@AllArgsConstructor
public class UrlController {

    private UrlService urlService ;

    @PostMapping("/shorten")
    public ResponseEntity<APIResponse<String>> shortenUrl( @Valid @RequestBody UrlRequest url) {

        String shorturl =  urlService.shortenUrl(url.getUrl());
        return ResponseEntity.ok(new APIResponse<String>(true, "successful", shorturl)) ;

       
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {

        String originalUrl = urlService.getOriginalUrl(code).getOriginalUrl();

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
        
    }
    
    

    

}
