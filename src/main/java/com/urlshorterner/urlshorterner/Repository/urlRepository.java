package com.urlshorterner.urlshorterner.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.urlshorterner.urlshorterner.Entity.Url;
import com.urlshorterner.urlshorterner.Entity.User;



public interface urlRepository extends JpaRepository<Url,Long> {

    public Optional<Url>  findByShortUrl(String shortUrl); 
    
    @Query("SELECT  u from Url u ORDER BY u.clicks DESC")
    List<Url> findTopUrls(Pageable pageable);


    Page<Url>  findByUser(User user,Pageable pageable);



}
