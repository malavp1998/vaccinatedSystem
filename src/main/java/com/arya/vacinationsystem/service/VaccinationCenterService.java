package com.arya.vacinationsystem.service;

import com.arya.vacinationsystem.entity.VaccinationCenter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VaccinationCenterService {

    VaccinationCenter registerCenter(VaccinationCenter vaccinationCenter);

    List<VaccinationCenter> getCenterByCityName(String cityName);

    List<VaccinationCenter> getCenterByPinode(String pincode);


}
