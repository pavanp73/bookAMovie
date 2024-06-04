package com.lld.bookamovie.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {

    private String number;
    private User user;
    // it is optional to have it as we already have list of show seats
    private Show show;
    private List<ShowSeat> showSeats;
    private BookingStatus bookingStatus;
    private List<Payment> payments;
    private int totalAmount;
}
