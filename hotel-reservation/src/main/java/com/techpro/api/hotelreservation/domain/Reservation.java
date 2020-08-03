package com.techpro.api.hotelreservation.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;



@DynamoDBTable(tableName = "reservation_db")
public class Reservation {

    public String bookingNumber;
    public String check_in_date;
    public String check_out_date;
    public Integer num_of_guest;
    public Integer num_of_rooms;
    public String email;
    public String dateCreated;
    public HotelDetails hotelDetails;
    public RoomDetails roomDetails;
    public GuestDetails guestDetails;
    public PaymentDetails paymentDetails;

    public Reservation(String bookingNumber, Integer num_of_guest, Integer num_of_rooms, String email) {
        this.num_of_guest = num_of_guest;
        this.num_of_rooms = num_of_rooms;
        this.bookingNumber = bookingNumber;
        this.email = email;
    }

    public Reservation() {
    }

    @Override
    public String toString() {
        return String.format(
                "Reservation[id=%s, Number of Rooms='%s', Number of Guests='%s, Email='%s']",
                bookingNumber, num_of_rooms, num_of_guest, email);
    }


    @DynamoDBAttribute(attributeName = "email")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "emailGSI")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @DynamoDBHashKey(attributeName = "bookingNumber")
    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }


    @DynamoDBAttribute(attributeName = "check_in_date")
    public String getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(String check_in_date) {
        this.check_in_date = check_in_date;
    }


    @DynamoDBAttribute(attributeName = "check_out_date")
    public String getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(String check_out_date) {
        this.check_out_date = check_out_date;
    }


    @DynamoDBAttribute(attributeName = "num_of_guest")
    public Integer getNum_of_guest() {
        return num_of_guest;
    }

    public void setNum_of_guest(Integer num_of_guest) {
        this.num_of_guest = num_of_guest;
    }


    @DynamoDBAttribute(attributeName = "num_of_rooms")
    public Integer getNum_of_rooms() {
        return num_of_rooms;
    }

    public void setNum_of_rooms(Integer num_of_rooms) {
        this.num_of_rooms = num_of_rooms;
    }


    @DynamoDBAttribute(attributeName = "dateCreated")
    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @DynamoDBAttribute(attributeName = "guestDetails")
    public GuestDetails getGuestDetails() {
        return guestDetails;
    }

    public void setGuestDetails(GuestDetails guestDetails) {
        this.guestDetails = guestDetails;
    }


    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }


    public void setRoomDetails(RoomDetails roomDetails) {
        this.roomDetails = roomDetails;
    }

    public RoomDetails getRoomDetails() {
        return roomDetails;
    }


    public void setHotelDetails(HotelDetails hotelDetails) {
        this.hotelDetails = hotelDetails;
    }

    public HotelDetails getHotelDetails() {
        return hotelDetails;
    }

}