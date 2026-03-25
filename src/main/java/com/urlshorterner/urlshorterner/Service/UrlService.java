package com.urlshorterner.urlshorterner.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.urlshorterner.urlshorterner.DT0.UrlAnalyticResponse;
import com.urlshorterner.urlshorterner.DT0.UrlRequest;
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

    public String shortenUrl( UrlRequest url){
        String OriginalUrl = url.getUrl().trim();
        String shortUrl ;
        if(url.getCustomcode() != null  &&  !url.getCustomcode().isEmpty()){
            if (urlRepository.findByShortUrl(url.getCustomcode()).isPresent()) {
                throw new RuntimeException("Custom code alredy exists");
                
            }
            shortUrl = url.getCustomcode();
            
        }
        else
        {
            shortUrl = Generatecode();
            while(urlRepository.findByShortUrl(shortUrl).isPresent()){
                shortUrl = Generatecode();
            }

        }


      
        Url urlentity = Url.builder()
                     .originalUrl(OriginalUrl)
                     .shortUrl(shortUrl)
                     .createdAt(LocalDateTime.now())
                     .clicks(0l)
                     .expiryTime(LocalDateTime.now().plusDays(7))
                     .build();

          urlRepository.save(urlentity);
          
          return  "http://localhost:8080/" + shortUrl;

    }

    public Url getOriginalUrl(String shortUrl){
        Url url  = urlRepository.findByShortUrl(shortUrl)
                   .orElseThrow(() ->  new RuntimeException("Url does not exist"));

        if(url.getExpiryTime()!=null &&  url.getExpiryTime().isBefore(LocalDateTime.now())){
            throw new RuntimeException("URL is expired ");
        }

          
        url.setClicks(url.getClicks()     +1   );
        urlRepository.save(url);   

                   
           return url;        
    }


    public List<UrlAnalyticResponse> findTopUrl(int limit ){
        Pageable pageable = PageRequest.of(0, limit);
        List<Url> list = urlRepository.findTopUrls(pageable);

        return list.stream().map( item ->
            UrlAnalyticResponse.builder()
                                .shortcode(item.getShortUrl())
                                .originalUrl(item.getOriginalUrl())
                                .clicks(item.getClicks())
                                .build()).toList();

    }

}
