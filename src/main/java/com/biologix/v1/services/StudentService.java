package com.biologix.v1.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.biologix.v1.entities.Device;
import com.biologix.v1.entities.Student;
import com.biologix.v1.repositories.StudentRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentService 
{

private final StudentRepo studentRepo;
private final DeviceService deviceService;

public Student addStudent(Student s, String deviceName)
{
    //checking for dublicate entries
    if((studentRepo.findByEnrollmentNo(s.getEnrollmentNo()).isPresent()))
    {
        return null;
    }
    //searching for the device from where the request is being recieved
    Device d = deviceService.findByDeviceName(deviceName);
    if(d!=null)
    {
        s.setDevice(d);
    }
    return studentRepo.save(s);
}

public Long noOfStudents()
{
    return studentRepo.count();
}

public Student findByFingerPrint(Long fingerPrint)
{
    return studentRepo.findByFingerPrint(fingerPrint).orElse(null);
}

public List<Student> getAllStudents()
{
    return studentRepo.findAll();
}

}
