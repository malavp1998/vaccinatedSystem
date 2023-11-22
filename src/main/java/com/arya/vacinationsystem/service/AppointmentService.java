package com.arya.vacinationsystem.service;

import com.arya.vacinationsystem.entity.Appointment;
import com.arya.vacinationsystem.entity.User;
import com.arya.vacinationsystem.entity.VaccinationCenter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface AppointmentService {
    Appointment bookAppointment(long aadharNumber, long centerId) throws Exception;

    List<Appointment> getAppointmentsAtStateLevel(String stateName);

    List<Appointment> getAppointmentsAtCenterLevel();
}
