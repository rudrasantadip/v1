package com.biologix.v1.services;

import org.springframework.stereotype.Service;

import com.biologix.v1.entities.FingerPrintData;
import com.biologix.v1.repositories.FingerRepo;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FingerService 
{

    private final FingerRepo fingerRepo;
    public FingerPrintData addFingerPrint(FingerPrintData fingerPrintData)
    {

        return fingerRepo.save(fingerPrintData);
    }

}
