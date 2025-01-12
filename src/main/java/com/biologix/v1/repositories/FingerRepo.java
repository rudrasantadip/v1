package com.biologix.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biologix.v1.entities.FingerPrintData;

public interface FingerRepo extends JpaRepository<FingerPrintData,Long> 
{

}
