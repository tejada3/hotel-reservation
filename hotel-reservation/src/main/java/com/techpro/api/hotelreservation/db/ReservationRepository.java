//package com.techpro.api.hotelreservation.db;
//
//import com.techpro.api.hotelreservation.domain.Reservation;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public interface ReservationRepository {
//    public Reservation findByBookingNumber(String bookingNumber);
//
//    public List<Reservation> findByEmail(String email);
//
//    public Reservation createNewReservation(Reservation newReservation);
//
//    public Reservation createNewReservation(String alphaNumericString, int i, int i1, String s);
//
//    List<Reservation> findAll();
//
//    //public void createNewReservation(Reservation r);
//}
//
//abstract class findByEmail implements ReservationRepository{
//
//    public List<Reservation> fin(String email) {
//        @Autowired
//        ReservationRepository reservationRepo;
//
//        List<Reservation> reservationList = reservationRepo.findAll();
//
//        @Override
//        public List<Reservation> findByEmail(String email){
//            for(Reservation reservation: reservationList){
//                //String guestEmail;
//                //String guestEmail = reservation.getPrimary_guest_details().get(email);
//                if(reservation.getPrimary_guest_details().containsValue(email)){
//                    reservationList.add(reservation);
//                }
//            }
//            return reservationList;
//        }
//        public static void main(String[] args){
//            findingByEmail(email);
//        }
//    }
//    }
//
//
//
//abstract class  findByEmail implements ReservationRepository{
//    @Autowired
//    ReservationRepository reservationRepo;
//    List<Reservation> reservationList = reservationRepo.findAll();
//    @Override
//    public List<Reservation> findByEmail(String email){
//         for(Reservation reservation: reservationList){
//             //String guestEmail;
//             //String guestEmail = reservation.getPrimary_guest_details().get(email);
//             if(reservation.getGuestDetails().get(email)){
//                 reservationList.add(reservation);
//             }
//         }
//        return reservationList;
//    }
//    public static void main(String[] args){
//        findingByEmail(email);
//    }

