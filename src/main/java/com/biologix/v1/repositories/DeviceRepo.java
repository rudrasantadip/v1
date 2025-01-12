package com.biologix.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biologix.v1.entities.Device;

import java.util.Optional;

public interface DeviceRepo extends JpaRepository<Device,Long> 
{
      @Query("SELECT d FROM Device d WHERE d.deviceName = :deviceName")
      Optional<Device> findByDeviceName(String deviceName);

      @Query("SELECT d.deviceStatus FROM Device d WHERE d.deviceName = :deviceName")
      Optional<String> findDeviceStatus(String deviceName);
}
