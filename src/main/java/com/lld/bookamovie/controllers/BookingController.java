package com.lld.bookamovie.controllers;

import com.lld.bookamovie.dtos.CreateBookingRequestDto;
import com.lld.bookamovie.dtos.CreateBookingResponseDto;
import com.lld.bookamovie.dtos.ResponseStatus;
import com.lld.bookamovie.models.Booking;
import com.lld.bookamovie.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public CreateBookingResponseDto createBooking(CreateBookingRequestDto requestDto) {
        CreateBookingResponseDto responseDto = new CreateBookingResponseDto();

        Booking booking = bookingService.createBooking(
                requestDto.getUserId(),
                requestDto.getShowId(),
                requestDto.getShowSeatIds()
        );

        responseDto.setBookingId(booking.getId());
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDto;
    }
}
