package com.restful.webservices.restfulwebservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.webservices.model.Payer;

@Repository
public interface PayerRepository extends JpaRepository<Payer, Integer> {

}
