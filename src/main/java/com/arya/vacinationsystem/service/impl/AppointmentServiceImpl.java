package com.arya.vacinationsystem.service.impl;

import com.arya.vacinationsystem.entity.Appointment;
import com.arya.vacinationsystem.entity.User;
import com.arya.vacinationsystem.entity.VaccinationCenter;
import com.arya.vacinationsystem.repository.AppointmentRepository;
import com.arya.vacinationsystem.repository.UserRepository;
import com.arya.vacinationsystem.repository.VaccinationCenterRepository;
import com.arya.vacinationsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {


    private UserRepository userRepository;


    private AppointmentRepository appointmentRepository;


    private VaccinationCenterRepository vaccinationCenterRepository;

    @Autowired
    public AppointmentServiceImpl(UserRepository userRepository, AppointmentRepository appointmentRepository, VaccinationCenterRepository vaccinationCenterRepository)
    {
        this.appointmentRepository =appointmentRepository;
        this.userRepository = userRepository;
        this.vaccinationCenterRepository = vaccinationCenterRepository;

    }

    private boolean isCenterOpen(VaccinationCenter center, LocalTime currentTime) {

        return currentTime.isAfter(center.getOpenTime()) && currentTime.isBefore(center.getCloseTime());
    }

    private boolean isCenterCapacityAvailable(VaccinationCenter center, LocalDateTime currentLocalDateTime) {
      //  LocalDateTime oneHourAgo = LocalDateTime.of(currentLocalDateTime.getHour() - 1, currentLocalDateTime.getMinute(), currentLocalDateTime.getSecond());
        final int Hours = 1;
        LocalDateTime oneHoursBefore = currentLocalDateTime.minusHours(Hours);
        long countAppointmentsWithinLastHour = center.getAppointments().stream()
                .filter(appointment -> appointment.getBookingDateTime().isAfter(oneHoursBefore))
                .count();
        return center.getMaxCapacityPerHour() > countAppointmentsWithinLastHour;

    }

    @Override
    public Appointment bookAppointment(long userId, long centerId) throws Exception {
        try {
            Optional<User> user = userRepository.findById(userId);
            Optional<VaccinationCenter> vaccinationCenter = vaccinationCenterRepository.findById(centerId);
//            System.out.println(user.isPresent() + " "+vaccinationCenter.isPresent());
            if (user.isPresent() && vaccinationCenter.isPresent()) {

                LocalTime currentTime = LocalTime.now();
                LocalDateTime currentLocalDateTime = LocalDateTime.now();
                if (user.get().isVaccinated()) {
                    throw new IllegalArgumentException("User is already vaccinated and cannot book a new appointment.");
                }

                else if (!isCenterOpen(vaccinationCenter.get(), currentTime)) {
                    throw new IllegalArgumentException("Center is closed and cannot book a new appointment.");
                }

                else if (!isCenterCapacityAvailable(vaccinationCenter.get(), currentLocalDateTime)) {
                    throw new IllegalArgumentException("Center capacity is full and cannot book a new appointment.");
                }
                else {
                    Appointment newAppointment = new Appointment(user.get(), vaccinationCenter.get(), LocalDateTime.now(), false);
                    appointmentRepository.save(newAppointment);
                    return newAppointment;
                }
            }

        } catch (Exception e) {

            System.out.println("Exception in bookAppointment "+ e.getMessage());
            throw e;

        }
        return null;

    }

    @Override
    public List<Appointment> getAppointmentsAtStateLevel(String stateName) {
        try {
            List<Appointment> appointment = appointmentRepository.findAll();
            if (appointment.size() != 0)
                return appointment.stream().filter(x -> x.getCenter().getState().equals(stateName)).collect(Collectors.toList());
        } catch (Exception e) {

        }
        return new ArrayList<>();
    }

    @Override
    public List<Appointment> getAppointmentsAtCenterLevel() {   // Assuming that I need to return all the available appointment
        try {
            return appointmentRepository.findAll();
        } catch (Exception e) {
            System.out.println("Exception in getAppointmentsAtCenterLevel " + e.getMessage());
            throw e;
        }
    }
}
