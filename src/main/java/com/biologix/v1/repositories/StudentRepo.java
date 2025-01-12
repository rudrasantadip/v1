package com.biologix.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biologix.v1.entities.Student;
import java.util.List;
import java.util.Optional;


public interface StudentRepo extends JpaRepository<Student,Long>
{
    @Query(value = "SELECT s FROM Student s WHERE s.fingerPrint = :fingerPrint")
    Optional<Student> findByFingerPrint(Long fingerPrint);

    @Query(value ="SELECT s FROM Student s WHERE s.enrollmentNo = :enrollmentNo" )
    Optional<Student> findByEnrollmentNo(String enrollmentNo);
}
