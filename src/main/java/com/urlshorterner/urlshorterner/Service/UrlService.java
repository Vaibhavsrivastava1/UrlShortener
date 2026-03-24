package com.urlshorterner.urlshorterner.Service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.urlshorterner.urlshorterner.Entity.Url;
import com.urlshorterner.urlshorterner.Repository.urlRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UrlService {


    private urlRepository urlRepository ;

     private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private final Random random = new Random();;


    private String Generatecode(){
        StringBuilder code = new StringBuilder();
        int l = CHAR_SET.length();
        for( int i = 0 ; i<CODE_LENGTH; i++){
            code.append(CHAR_SET.charAt(random.nextInt(l)));
        }
        return code.toString() ;
    }

    public String shortenUrl( String OriginalUrl){
        OriginalUrl = OriginalUrl.trim();
       


        String shortUrl = Generatecode();

        while(urlRepository.findByShortUrl(shortUrl).isPresent()){
            shortUrl = Generatecode();
        }

        Url url = Url.builder()
                     .originalUrl(OriginalUrl)
                     .shortUrl(shortUrl)
                     .createdAt(LocalDateTime.now())
                     .build();

          urlRepository.save(url);
          
          return  "http://localhost:8080/" + shortUrl;

    }

    public Url getOriginalUrl(String shortUrl){
        Url url  = urlRepository.findByShortUrl(shortUrl)
                   .orElseThrow(() ->  new RuntimeException("Url does not exist"));

                   
           return url;        
    }

}
