package com.biologix.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biologix.v1.entities.Attendance;


public interface AttendanceRepo extends JpaRepository<Attendance,Long> 
{

}
