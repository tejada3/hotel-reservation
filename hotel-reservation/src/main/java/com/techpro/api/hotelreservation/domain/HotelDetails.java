package com.techpro.api.hotelreservation.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;


@DynamoDBDocument
public class HotelDetails {
    private String hotel_name;
    private String hotel_address;
    private String hotel_phone;
    public Double reservation_PricePerNight;


    @DynamoDBAttribute(attributeName = "reservation_pricePerNight")
    public Double getReservation_PricePerNight() {
        return reservation_PricePerNight;
    }

    public void setReservation_PricePerNight(Double reservation_PricePerNight) {
        this.reservation_PricePerNight = reservation_PricePerNight;
    }

    @DynamoDBAttribute(attributeName = "hotel_name")
    public String getHotel_name() {
        return hotel_name;
    }
    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    @DynamoDBAttribute(attributeName = "hotel_address")
    public String getHotel_address() {
        return hotel_address;
    }
    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    @DynamoDBAttribute(attributeName = "hotel_phone")
    public String getHotel_phone() {
        return hotel_phone;
    }
    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

}