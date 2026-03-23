package com.urlshorterner.urlshorterner.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urlshorterner.urlshorterner.Entity.Url;

public interface urlRepository extends JpaRepository<Url,Long> {

}
