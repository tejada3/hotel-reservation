package com.techpro.api.hotelreservation.util;

import com.techpro.api.hotelreservation.domain.Reservation;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalculateTotalPrice {
    public static void calculateTotalPrice(Reservation r){
//        double num = (r.hotelDetails.getPricePerNight() * r.getNum_of_guest())*r.getNum_of_rooms();
//        DecimalFormat df = new DecimalFormat("#.##");
//        num = Double.parseDouble(df.format(num));
        r.paymentDetails.setReservation_total_price((r.hotelDetails.getReservation_PricePerNight() * r.getNum_of_guest())*r.getNum_of_rooms());
    }
}
