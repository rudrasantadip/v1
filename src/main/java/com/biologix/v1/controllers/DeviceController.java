package com.biologix.v1.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biologix.v1.entities.ApiResponse;
import com.biologix.v1.entities.Device;
import com.biologix.v1.services.DeviceService;

import lombok.RequiredArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("device")
@CrossOrigin("*")
public class DeviceController 
{


    private final DeviceService deviceService;

    @PostMapping("add")
    public ResponseEntity<Boolean> addDevice(@RequestBody Device d)
    {
        return ResponseEntity.ok(deviceService.addDevice(d));
    }

    @GetMapping("info")
    public ResponseEntity<String> deviceInfo(@RequestParam("devicename")String deviceName)
    {
        return ResponseEntity.ok(deviceService.getDeviceInfo(deviceName));
    }

    @GetMapping("update/status")
    public ResponseEntity<ApiResponse>updateDeviceStatus(@RequestParam("deviceId")Long deviceId, @RequestParam("deviceStatus")String deviceStatus)
    {
        ApiResponse response = ApiResponse.builder()
        .message("success")
        .status(deviceService.updateDeviceStatus(deviceId, deviceStatus))
        .build();

        return ResponseEntity.ok(response);
    }
}
