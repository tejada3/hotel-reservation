package com.techpro.api.hotelreservation.contoller;

import com.techpro.api.hotelreservation.domain.Reservation;
import com.techpro.api.hotelreservation.exception.ReservationException;
import com.techpro.api.hotelreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ReservationsController {

    @Autowired
    ReservationService reservationService;

    @PreAuthorize("(#oauth2.hasScope('reservation') and #oauth2.hasScope('write')) or hasRole('ADMIN')")
    @PostMapping("/reservation")
    @ResponseBody
    public Reservation createReservation(@RequestBody Reservation newReservation) {
        Reservation r = reservationService.createReservation(newReservation);
        return r;
    }

    @PreAuthorize("(#oauth2.hasScope('reservation') and #oauth2.hasScope('read')) or hasRole('ADMIN')")
    @GetMapping("reservation/{bookingNumber}")
    public ResponseEntity<?> getReservationBookingNumber(@PathVariable final String bookingNumber) {
        Reservation r = reservationService.getResBookingNumber(bookingNumber);
        return ResponseEntity.ok().body(r);
    }

    @PreAuthorize("(#oauth2.hasScope('reservation') and #oauth2.hasScope('write')) or hasRole('ADMIN')")
    @DeleteMapping("/reservation/{bookingNumber}")
    public void deleteReservation(@PathVariable final String bookingNumber) {
        reservationService.deleteResBookingnumber(bookingNumber);

    }
    @PreAuthorize("(#oauth2.hasScope('reservation') and #oauth2.hasScope('write')) or hasRole('ADMIN')")
    @PutMapping("/reservation/{bookingNumber}")
    public ResponseEntity<?> updateReservation(@PathVariable final String bookingNumber, @RequestBody Reservation newReservation){
        Reservation r = null;
        try {
            r = reservationService.updateReservation(newReservation, bookingNumber);

        } catch (ReservationException re) {
            return ResponseEntity.badRequest().body("Reservation Does Not Exist");
        }
        return ResponseEntity.ok().body(r);
    }


    @GetMapping("/reservation")
    public List<Reservation> getReservationByEmail(@RequestParam final String email){
            List<Reservation> r = reservationService.getBookingNumberbyEmail(email);
            return r;

    }

    @GetMapping("/reservation/guest")
    public List<Reservation> getReservationByGuestEmail(@RequestParam final String email){
        List<Reservation> r = reservationService.reservationByGuestEmail(email);
        return r;

    }

}