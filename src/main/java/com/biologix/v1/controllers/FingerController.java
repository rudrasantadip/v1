package com.biologix.v1.controllers;

import java.time.LocalDate;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biologix.v1.entities.Attendance;
import com.biologix.v1.entities.FingerPrintData;
import com.biologix.v1.entities.Student;
import com.biologix.v1.services.AttendanceService;
import com.biologix.v1.services.FingerService;
import com.biologix.v1.services.StudentService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;




@RestController
@RequestMapping("/api/fingerprint")
@CrossOrigin("*")
@RequiredArgsConstructor
public class FingerController 
{

    
    @Autowired
    private  FingerService fingerService;
    @Autowired
    private  StudentService studentService;
    @Autowired
    private  AttendanceService attendanceService;

    @Autowired
    private  Sinks.Many<String> eventSink;
    @Autowired
    private Sinks.Many<String> eventSink2;


   @PostMapping("/upload")
    public ResponseEntity<String> uploadFingerprint(@RequestBody FingerPrintData data) {
        // Log or save the fingerprint data
        
        //create a student entity and add the figner print details and save the student entity to the db
        Student s = Student.builder().
        fingerPrint(Long.parseLong(data.getFingerprintData()))
        .fingerPrintTemplate(data)
        .build();


        //calling student service and saving the student to the db
        studentService.addStudent(s,data.getDeviceInfo());

        //save the finger print to the db
        data.setStudent(s);
        fingerService.addFingerPrint(data); // save the finger print data to the database
        eventSink2.tryEmitNext(data.getFingerprintData());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @GetMapping("/attendance")
    public ResponseEntity<String> attendance(@RequestParam("fingerId") String fingerId)
    {
        Student s = studentService.findByFingerPrint(Long.parseLong(fingerId));

        Attendance a = Attendance.builder()
        .date(LocalDate.now())
        .present(true)
        .student(s)
        .build();

        s.getAttendanceRecords().add(a);
        System.out.println(s.getEnrollmentNo());

        studentService.addStudent(s, s.getDevice().getDeviceName());
        attendanceService.addAttendance(a);
        eventSink.tryEmitNext(s.getStudentName()+"::"+s.getEnrollmentNo()+"::"+s.getRegistrationNo());
        return ResponseEntity.ok(s.getStudentName());
    }


    @GetMapping(value = "/sse", produces = "text/event-stream")
    public Flux<ServerSentEvent<String>> sendMessages() {
         return eventSink.asFlux().map(data -> ServerSentEvent.builder(data).build())
         .doOnCancel(() -> System.out.println("Client disconnected, stopping stream."));
    }

    @GetMapping(value = "/sse2", produces = "text/event-stream")
    public Flux<ServerSentEvent<String>> addedFinger() {
         return eventSink.asFlux().map(data -> ServerSentEvent.builder(data).build())
         .doOnCancel(() -> System.out.println("Client disconnected, stopping stream."));
    }
}
