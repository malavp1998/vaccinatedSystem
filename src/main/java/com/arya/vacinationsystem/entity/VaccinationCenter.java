package com.arya.vacinationsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String state;

    private String city;

    private String pinCode;

    private int maxCapacityPerHour;

    private LocalTime openTime;

    private LocalTime closeTime;

    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL)
    private List<Appointment> appointments;


}
