package com.lld.bookamovie.services;

import com.lld.bookamovie.models.Booking;
import com.lld.bookamovie.models.BookingStatus;
import com.lld.bookamovie.models.Show;
import com.lld.bookamovie.models.ShowSeat;
import com.lld.bookamovie.models.ShowSeatStatus;
import com.lld.bookamovie.models.User;
import com.lld.bookamovie.repositories.BookingRepository;
import com.lld.bookamovie.repositories.ShowRepository;
import com.lld.bookamovie.repositories.ShowSeatRepository;
import com.lld.bookamovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          ShowSeatRepository showSeatRepository,
                          ShowRepository showRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE) /* applying lock at method */
    public Booking createBooking(Long userId, Long showId, List<Long> showSeatIds) {

        /*
          1. Get the user from DB by iven id
          2. get the show from DB by given id
          3. get the list of show seats from DB by given ids
          ------------ Take a lock ----------------
          4. Check if all the seats are available
          5. if not, throw exception
          6. if yes, mark the status of all the seats as BLOCKED
          ------------ Release lock ---------------
          7. save the changes to DB
          8. create the booking with pending status [save booking obj to DB]
          9. return the booking object
         */
        Booking booking = new Booking();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User was not found");
        }
        booking.setUser(optionalUser.get());
        Optional<Show> optionalShow = showRepository.findById(showId);
        if (optionalShow.isEmpty()) {
            throw new RuntimeException("Show was not found");
        }
        booking.setShow(optionalShow.get());

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        boolean isAllSeatsAvailable = isAllSeatsAvailable(showSeats);

        if (!isAllSeatsAvailable) {
            throw new RuntimeException("Some or all seats are not available");
        }

        // mark the status of all the seats as BLOCKED and save the changes to DB
        showSeatRepository.saveAll(showSeats.stream()
                .peek(showSeat -> showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED))
                .collect(Collectors.toList())
        );

        booking.setShowSeats(showSeats);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setTotalAmount(450);
        booking.setNumber("BLR34567");

        booking = bookingRepository.save(booking);
        return booking;
    }

    private boolean isAllSeatsAvailable(List<ShowSeat> showSeats) {
        for (ShowSeat showSeat : showSeats) {
            if (!ShowSeatStatus.AVAILABLE.equals(showSeat.getShowSeatStatus())) {
                return false;
            }
        }
        return true;
    }
}
