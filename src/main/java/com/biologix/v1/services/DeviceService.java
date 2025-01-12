package com.biologix.v1.services;

import org.springframework.stereotype.Service;

import com.biologix.v1.entities.Device;
import com.biologix.v1.entities.User;
import com.biologix.v1.repositories.DeviceRepo;
import com.biologix.v1.repositories.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeviceService 
{

    private final DeviceRepo deviceRepo;

    public Boolean addDevice(Device d)
    {
        if(d!=null)
        {
            deviceRepo.save(d);
            return true;
        }
        return false;
    }

    public String getDeviceInfo(String deviceName)
    {
        return deviceRepo.findDeviceStatus(deviceName).orElse("nill");
    }

    public Device findByDeviceName(String deviceName)
    {
        return deviceRepo.findByDeviceName(deviceName).orElse(null);
    }

    public Boolean updateDeviceStatus(Long deviceId, String deviceStatus)
    {
        Device d =deviceRepo.findById(deviceId).orElse(null);
        if(d!=null)
        {
            d.setDeviceStatus(deviceStatus);
            deviceRepo.save(d);
            return true;
        }
        return false;
    }

}
