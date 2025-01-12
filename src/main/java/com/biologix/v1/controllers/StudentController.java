package com.biologix.v1.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biologix.v1.entities.Student;
import com.biologix.v1.services.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController 
{
    private final StudentService studentService;


    @GetMapping()
    public List<Student>getAllStudents()
    {
        return studentService.getAllStudents();
    }

    @PostMapping("add")
    public Student add(@RequestBody Student s, @RequestParam("deviceName") String deviceName)
    {
        Student sdb = studentService.findByFingerPrint(s.getFingerPrint());
        if(sdb!=null)
        {
            sdb.setStudentName(s.getStudentName());
            sdb.setEnrollmentNo(s.getEnrollmentNo());
            sdb.setRegistrationNo(s.getRegistrationNo());
            sdb.setStream(s.getStream());
            sdb.setRollNo(s.getRollNo());
            sdb.setYear(s.getYear());
            sdb.setSection(s.getSection());
        }
        return studentService.addStudent(sdb, deviceName);
    }
}
