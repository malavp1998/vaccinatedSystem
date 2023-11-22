package com.arya.vacinationsystem.service.impl;

import com.arya.vacinationsystem.entity.VaccinationCenter;
import com.arya.vacinationsystem.repository.UserRepository;
import com.arya.vacinationsystem.repository.VaccinationCenterRepository;
import com.arya.vacinationsystem.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccinationCenterServiceImpl implements VaccinationCenterService {


    private VaccinationCenterRepository vaccinationCenterRepository;

    @Autowired
    public VaccinationCenterServiceImpl(VaccinationCenterRepository vaccinationCenterRepository) {
        this.vaccinationCenterRepository = vaccinationCenterRepository;
    }

    @Override
    public VaccinationCenter registerCenter(VaccinationCenter vaccinationCenter) {
        try {
            return vaccinationCenterRepository.save(vaccinationCenter);
        } catch (Exception e) {
            System.out.println("Exception in registerCenter " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<VaccinationCenter> getCenterByCityName(String cityName) {
        try {
            List<VaccinationCenter> vaccinationCenter = vaccinationCenterRepository.findAll();
            return vaccinationCenter.stream().filter(center -> center.getCity().equalsIgnoreCase(cityName)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Exception in getCenterByCityName " + e.getMessage());
            throw e;
        }

    }

    @Override
    public List<VaccinationCenter> getCenterByPinode(String pincode) {
        try {
            List<VaccinationCenter> vaccinationCenter = vaccinationCenterRepository.findAll();
            return vaccinationCenter.stream().filter(center -> center.getPinCode().equalsIgnoreCase(pincode)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Exception in getCenterByCityName " + e.getMessage());
            throw e;
        }
    }
}
