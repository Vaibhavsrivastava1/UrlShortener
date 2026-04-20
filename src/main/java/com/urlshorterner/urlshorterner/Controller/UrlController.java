package com.urlshorterner.urlshorterner.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.urlshorterner.urlshorterner.DT0.APIResponse;
import com.urlshorterner.urlshorterner.DT0.UrlAnalyticResponse;
import com.urlshorterner.urlshorterner.DT0.UrlRequest;
import com.urlshorterner.urlshorterner.Service.UrlService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RequestMapping("/user")
@RestController
@AllArgsConstructor
public class UrlController {

    private UrlService urlService ;

   
    

    @PostMapping("/shorten")
    public ResponseEntity<APIResponse<String>> shortenUrl( @Valid @RequestBody UrlRequest url) {


      
        String shorturl =  urlService.shortenUrl(url );
        return ResponseEntity.ok(new APIResponse<String>(true, "successful", shorturl)) ;

       
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {

        String originalUrl = urlService.getOriginalUrl(code).getOriginalUrl();
        

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
        
    }

    @GetMapping("/my-url")
    public ResponseEntity<APIResponse<List<UrlAnalyticResponse>>> getMyUrl( @RequestParam( required = false, defaultValue = "0") int pageNumber, @RequestParam(required = false , defaultValue = "5") int pageSize) {
        return ResponseEntity.ok(new APIResponse<List<UrlAnalyticResponse>>(true, "successfull", urlService.getMyUrl(PageRequest.of(pageNumber, pageSize) )));
    }
    

    @GetMapping("/analytics/top")
    public ResponseEntity<APIResponse<List<UrlAnalyticResponse>>> getTopUrls(  @RequestParam(defaultValue = "5") int limit) {
        List<UrlAnalyticResponse> items = urlService.findTopUrl(limit);

        return ResponseEntity.ok(new APIResponse<List<UrlAnalyticResponse>>(true, "successfull", items));
       

    }
    
    
    

    

}
