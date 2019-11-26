package com.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.Lei;

@Repository
public interface LeiRepository extends JpaRepository<Lei, Integer>{

}