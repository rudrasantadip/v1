package com.biologix.v1.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attendance")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendance 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    private LocalDate date;
    private boolean present;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private Student student;
}
