package com.arya.vacinationsystem.controller;

import com.arya.vacinationsystem.entity.User;
import com.arya.vacinationsystem.entity.VaccinationCenter;
import com.arya.vacinationsystem.service.UserService;
import com.arya.vacinationsystem.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/center")
public class VaccinationCenterController {

    @Autowired
    private VaccinationCenterService vaccinationCenterService;

    @PostMapping("/register")
    private ResponseEntity<?> registerCenter(@RequestBody VaccinationCenter vaccinationCenter) {

        try {

            VaccinationCenter createdCenter = vaccinationCenterService.registerCenter(vaccinationCenter);
            if (createdCenter != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(createdCenter);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
            }
        } catch (Exception e) {
            System.out.println("Exception in registerCenter " + e.getMessage());

        }
        return null;
    }

    @GetMapping("/cityname/{cityName}")
    private List<VaccinationCenter> getCenterByCityName(@PathVariable("cityName") String cityName) {
        try {
            return vaccinationCenterService.getCenterByCityName(cityName);
        } catch (Exception e) {
            System.out.println("Exception in getCenterByCityName " + e.getMessage());
        }
        return null;
    }

    @GetMapping("/pincode/{pincode}")
    private List<VaccinationCenter> getCenterByPincode(@PathVariable("pincode") String pincode) {
        try {
            return vaccinationCenterService.getCenterByPinode(pincode);
        } catch (Exception e) {
            System.out.println("Exception in getCenterByPincode " + e.getMessage());
        }
        return null;
    }


}
