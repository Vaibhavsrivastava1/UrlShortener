package com.urlshorterner.urlshorterner.Entity;


import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Url")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id ;

    @Column(name = "original_url", nullable = false, length = 2048)
    private String originalUrl;
    
    @Column(name = "short_url", unique = true)
    private String shortUrl;
    @Column(name = "created_at")
    private LocalDateTime createdAt ;
    @Column(name = "click_count")
    private Long clicks = 0l ;
    @Column(name = "expiration_Time")
    private LocalDateTime expiryTime ;


}
