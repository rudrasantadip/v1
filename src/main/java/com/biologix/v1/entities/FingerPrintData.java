package com.biologix.v1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "fingertable") 
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FingerPrintData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "finger_id")
    private Long fingerId;

    private String fingerprintData;
    private String deviceInfo;

    @OneToOne(mappedBy = "fingerPrintTemplate",cascade = CascadeType.ALL)
    @JsonIgnore
    private Student student;
}
