package com.lld.bookamovie.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * Mapping Table
 */
@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel {

    private Show show;
    private Seat seat;
    private ShowSeatStatus showSeatStatus;

}
