package com.urlshorterner.urlshorterner.Service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.urlshorterner.urlshorterner.DT0.UrlAnalyticResponse;
import com.urlshorterner.urlshorterner.DT0.UrlRequest;
import com.urlshorterner.urlshorterner.Entity.Url;
import com.urlshorterner.urlshorterner.Entity.User;
import com.urlshorterner.urlshorterner.Repository.UserRepository;
import com.urlshorterner.urlshorterner.Repository.urlRepository;
import com.urlshorterner.urlshorterner.Util.AuthUtil;
import com.urlshorterner.urlshorterner.Util.Base62Encoder;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UrlService {


    private UserRepository userRepository;
    private urlRepository urlRepository ;
    private Base62Encoder base62Encoder;
    private AuthUtil authUtil;

    private final String BASE_URL = "http://localhost:8080/";

    


  

    public String shortenUrl( UrlRequest url ){

        String email = authUtil.getCurrentUserEmail();


        User user = userRepository.findByEmail(email)
                    .orElseThrow(()->  new RuntimeException("User not found"));
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
           Url temp = Url.builder()
                         .originalUrl(OriginalUrl)
                         .clicks(0L)
                         .createdAt(LocalDateTime.now())
                         .expiryTime(LocalDateTime.now().plusDays(7))
                         .user(user)
                         .build();

             temp =  urlRepository.save(temp);
             shortUrl = base62Encoder.encode(temp.getId());
             
             temp.setShortUrl(shortUrl);
             urlRepository.save(temp);

        }
        if(url.getCustomcode() != null  &&  !url.getCustomcode().isEmpty()){

            Url urlEntity = Url.builder()
                         .originalUrl(OriginalUrl)
                         .shortUrl(url.getCustomcode())
                         .clicks(0l)
                         .createdAt(LocalDateTime.now())
                         .expiryTime(LocalDateTime.now().plusDays(7))
                         .build();
            urlRepository.save(urlEntity);             
        }


      
       
          return  BASE_URL + shortUrl;

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

    public List<UrlAnalyticResponse> getMyUrl(PageRequest page) {

        String email = authUtil.getCurrentUserEmail();

        User user =  userRepository.findByEmail(email)
                     .orElseThrow(() -> new RuntimeException("User not found"));

          return urlRepository.findByUser(user, page).stream()
                               .map( r-> UrlAnalyticResponse.builder()
                                        .originalUrl(r.getOriginalUrl())
                                         .shortcode(r.getShortUrl())
                                          .clicks(r.getClicks())
                                          .build() ).toList();
                                                   
                                             
    }

}
