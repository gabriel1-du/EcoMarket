package com.example.ApiMedioDePago.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ApiMedioDePago.Model.MedioDePago;

@Repository
public interface MedioDePagoRepository extends JpaRepository<MedioDePago, Integer> {

}
