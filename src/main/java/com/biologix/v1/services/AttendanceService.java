package com.biologix.v1.services;

import org.springframework.stereotype.Service;

import com.biologix.v1.entities.Attendance;
import com.biologix.v1.repositories.AttendanceRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceService 
{

    private final AttendanceRepo attendanceRepo;

    public Boolean addAttendance(Attendance a)
    {
        if(attendanceRepo.save(a)!=null)
        {
            return true;
        }
        return false;
    }

}
