package com.arya.vacinationsystem.repository;

import com.arya.vacinationsystem.entity.Appointment;
import com.arya.vacinationsystem.entity.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, Long> {

}
