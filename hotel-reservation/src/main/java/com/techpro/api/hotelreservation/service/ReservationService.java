package com.techpro.api.hotelreservation.service;

import com.fasterxml.jackson.databind.util.ISO8601Utils;
import com.techpro.api.hotelreservation.db.DynamoDbUtil;
import com.techpro.api.hotelreservation.domain.Reservation;
import com.techpro.api.hotelreservation.exception.ReservationException;
import com.techpro.api.hotelreservation.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.techpro.api.hotelreservation.util.CalculateTotalPrice;

import java.util.Date;
import java.util.List;

@Service
public class ReservationService {


    @Autowired
    DynamoDbUtil dynamoDbUtil;

    public Reservation createReservation(Reservation newReservation) {
        String bookingNumber = RandomString.getAlphaNumericString(8);
        newReservation.setBookingNumber(bookingNumber);
        CalculateTotalPrice.calculateTotalPrice(newReservation);
        Date currentDate = new Date();
        String isoDateStr = ISO8601Utils.format(currentDate);
        newReservation.setDateCreated(isoDateStr);
        Reservation r = dynamoDbUtil.saveReservation(newReservation);
        return newReservation;
    }

    public Reservation getResBookingNumber(String bookingNumber) {
        Reservation r = dynamoDbUtil.findByBN(bookingNumber);
        return r;

    }

    public void deleteResBookingnumber(String bookingNumber) {

        Reservation r = dynamoDbUtil.findByBN(bookingNumber);
        dynamoDbUtil.deleteByBookingNumber(bookingNumber);
    }

    public Reservation updateReservation(Reservation updatedReservation, String bookingNumber) throws ReservationException {
        Reservation currentReservation = dynamoDbUtil.findByBN(bookingNumber);

        if(currentReservation == null) {
            throw new ReservationException("reservation does not exist",4001, HttpStatus.NOT_FOUND);
        }

        updatedReservation.setBookingNumber(currentReservation.getBookingNumber());
        Reservation modifiedReservation = dynamoDbUtil.saveReservation(updatedReservation);

        return modifiedReservation;
    }


    public List<Reservation> getBookingNumberbyEmail(String email){
        List<Reservation> r = dynamoDbUtil.getReservationByEmail(email);
        return r;
    }

    public List<Reservation> reservationByGuestEmail(String email){
        List<Reservation> r = dynamoDbUtil.getReservationByGuestEmail(email);
        return r;
    }
}

