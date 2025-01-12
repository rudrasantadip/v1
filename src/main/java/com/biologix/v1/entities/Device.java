package com.biologix.v1.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "devices")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceID;
    private String deviceName;
    private String deviceAdmin;
    private String deviceStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<Student> students; 

}
