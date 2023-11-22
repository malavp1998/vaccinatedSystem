package com.arya.vacinationsystem.controller;


import com.arya.vacinationsystem.entity.Appointment;
import com.arya.vacinationsystem.repository.AppointmentRepository;
import com.arya.vacinationsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService)
    {
        this.appointmentService = appointmentService;
    }


    @PostMapping("/book/{centerId}/{aadharNumber}")
    private Map<String,Object> bookAppointment(@PathVariable long aadharNumber, @PathVariable long centerId)
    {
        Map<String,Object> res  = new HashMap<>();

        Appointment appointment = null;
        String message = "";

        try {
          appointment=  appointmentService.bookAppointment(aadharNumber,centerId);
        }
        catch (Exception e)
        {
            message = e.getMessage();
            System.out.println("Exception in bookAppointment "+ e.getMessage());
        }

        res.put("data",appointment);
        res.put("message", message);
        return res;


    }

    @GetMapping("/{state}")
    private List<Appointment> getAppointmentByState(@PathVariable String state)
    {
        try {
            return appointmentService.getAppointmentsAtStateLevel(state);
        }
        catch (Exception e)
        {
            System.out.println("Exception in getAppointmentByState "+ e.getMessage());
        }
        return null;
    }
    @GetMapping("/all")
    private List<Appointment> getAppointmentByCenter()
    {
        try {
            return appointmentService.getAppointmentsAtCenterLevel();
        }
        catch (Exception e)
        {
            System.out.println("Exception in getAppointmentByCenter "+ e.getMessage());
        }
        return null;

    }


}
