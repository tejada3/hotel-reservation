package com.techpro.api.hotelreservation.db;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.techpro.api.hotelreservation.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DynamoDbUtil {

//    DynamoDB dynamoDB;
    @Autowired
    AmazonDynamoDB amazonDynamoDBClient;

    @Autowired
    DynamoDBMapper dynamoDBMapper, mapper;


    public Reservation saveReservation(Reservation reservation) {


        DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .build();


        dynamoDBMapper.save(reservation);
        return mapper.load(Reservation.class, reservation.getBookingNumber());
    }

    public Reservation findByBN(String bn){
        DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .build();

//        amazonDynamoDBClient = AmazonDynamoDBClientBuilder.standard()
//                .withRegion(Regions.DEFAULT_REGION)
//                .withCredentials(new AWSCredentialsProviderChain())
//                .build();
        return mapper.load(Reservation.class, bn);

    }

    public void deleteByBookingNumber(String bn){
        DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .build();

        Reservation r = mapper.load(Reservation.class,bn);
        mapper.delete(r);
    }

    public List<Reservation> getReservationByEmail(String email) {

        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(email));


        DynamoDBQueryExpression<Reservation> queryExpression = new DynamoDBQueryExpression<Reservation>()
                .withIndexName("email-index")
                .withConsistentRead(false)
                .withKeyConditionExpression("email = :val1").withExpressionAttributeValues(eav);

        List<Reservation> list = mapper.query(Reservation.class, queryExpression);
        return list;
    }

    public List<Reservation> getReservationByGuestEmail(String email) {

        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(email));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("guestDetails.email = :val1").withExpressionAttributeValues(eav);

        List<Reservation> scanResult = dynamoDBMapper.scan(Reservation.class, scanExpression);

        /*for (Reservation book : scanResult) {
            System.out.println(book);
        }*/
        return scanResult;
    }

}
