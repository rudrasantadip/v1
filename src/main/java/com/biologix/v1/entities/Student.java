package com.biologix.v1.entities;

import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_table")
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Student 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String studentName;
    private String enrollmentNo;
    private String registrationNo;
    private String stream;
    private String year;
    private String section;
    private String rollNo;
    private Long fingerPrint;

  
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "finger_id", referencedColumnName = "finger_id")
    private FingerPrintData fingerPrintTemplate;

    @ManyToOne
    @JoinColumn(name = "device_id")
    @JsonIgnore
    private Device device;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendanceRecords;
}
