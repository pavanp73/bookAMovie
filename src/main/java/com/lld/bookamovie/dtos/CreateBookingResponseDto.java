package com.lld.bookamovie.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookingResponseDto {

    private Long bookingId;
    private ResponseStatus responseStatus;
}
