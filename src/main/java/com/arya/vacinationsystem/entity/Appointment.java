package com.arya.vacinationsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @ManyToOne()
    @JoinColumn(name = "center_id")
    @JsonIgnore
    private VaccinationCenter center;

    private LocalDateTime bookingDateTime;

    private boolean vaccinated;

    public Appointment(User user, VaccinationCenter center, LocalDateTime bookingDateTime, boolean vaccinated) {
        this.user = user;
        this.center = center;
        this.bookingDateTime = bookingDateTime;
        this.vaccinated = vaccinated;
    }
}
