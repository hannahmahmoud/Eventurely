package com.website.bookingSystem.Servics;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.website.bookingSystem.Repository.bookingRepo;
import com.website.bookingSystem.Repository.eventRepo;
import com.website.bookingSystem.Validators.bookingValidator;
import com.website.bookingSystem.Model.*;

@Service
public class bookingServics {
    @Autowired
    bookingRepo bookingRepo;
    Events event;
    booking booking;
    @Autowired
    eventRepo eventRepo;

    public ResponseEntity<Object> bookEvent(Long eventId, Long userId) { 
        Map<String, Object> response = new HashMap<>();
        boolean exitedEvent = eventRepo.existsById(eventId);
        if (!exitedEvent) {
            response.put("status", "failed");
            response.put("message", "Event not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        bookingValidator bookingValidator = new bookingValidator(userId, eventId);
        booking = new booking(bookingValidator.getUserId(), bookingValidator.getEventId(), LocalDateTime.now());
        bookingRepo.save(booking);
        response.put("status", "success");
        response.put("reservation", booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // desc: func to get bookings by userId
    // route: GET /api/v1/booking/
    public ResponseEntity<Object> getBooking(Long userId) {
        Map<String, Object> response = new HashMap<>();
        List<booking> bookings = bookingRepo.findByUserId(userId);
        if (bookings.isEmpty()) {
            response.put("status", "failed");
            response.put("message", "User has not reserved any events yet.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response.put("status", "success");
        response.put("reservations", bookings);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // desc: func to delete by id  
    // route: DELETE /api/v1/booking/deleteReservation/{id}
    public ResponseEntity<Object> deleteReservation(Long id) {
        Map<String, Object> response = new HashMap<>();
        boolean existsReservation = bookingRepo.existsById(id);
        if (!existsReservation) {
            response.put("status", "failed");
            response.put("message", "Reservation not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        bookingRepo.deleteById(id);
        response.put("status", "success");
        response.put("message", "Reservation deleted successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}