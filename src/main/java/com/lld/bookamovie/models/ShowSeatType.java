package com.lld.bookamovie.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Mapping table
 */
@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel {

    private Show show;
    private SeatType seatType;
    private int price;
}
