package com.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.domain.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer>{

}