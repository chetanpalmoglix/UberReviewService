package com.example.UberReviewService.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" , "bookings"})
public class Passenger extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "passenger")
    private List<Booking> bookings=new ArrayList<>();
}
