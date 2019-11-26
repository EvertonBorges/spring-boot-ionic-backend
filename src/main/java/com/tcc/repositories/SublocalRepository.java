package com.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.Sublocal;

@Repository
public interface SublocalRepository extends JpaRepository<Sublocal, Integer>{

}