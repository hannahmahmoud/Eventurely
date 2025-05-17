package com.website.bookingSystem.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.bookingSystem.Model.AuthUser;
import com.website.bookingSystem.Servics.bookingServics;
import com.website.bookingSystem.Servics.userServics;


@RestController
@CrossOrigin
@RequestMapping("api/v1/booking")
public class bookingController {
    @Autowired 
    private bookingServics bookingServics;
    @Autowired
    private userServics userServics;

         @GetMapping("/")
    public ResponseEntity<Object>  getEvent(@RequestHeader ("Authorization") String authHeader) {
        //TODO: process get request
          Map<String, Object> response = new HashMap<>();
        AuthUser user= userServics.validateUser (authHeader);
          if (user==null) {
            
            response.put("Status","Failed");
            response.put("Message", "UNAUTHORIZED USER");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            
          }
      
        return bookingServics.getBooking(user.getId());
    }

    @PostMapping( value = "/newReservation/{eventId}", produces = "application/json")// ashan kda asmha post mapping 
    public ResponseEntity<Object> newEvent(@RequestHeader ("Authorization") String authHeader, @PathVariable  Long eventId) {
          Map<String, Object> response = new HashMap<>();
        AuthUser user= userServics.validateUser (authHeader);
          if (user==null) {
            
            response.put("Status","Failed");
            response.put("Message", "UNAUTHORIZED USER");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            
          }
         
 
        return  bookingServics.bookEvent(eventId,user.getId() );
       
    }

    @DeleteMapping( value = "deleteReservation/{id}")
    public ResponseEntity<Object> deleteEvent(@RequestHeader ("Authorization") String authHeader,@PathVariable Long id) 
    {
        //TODO: process delete request
          Map<String, Object> response = new HashMap<>();
        AuthUser user= userServics.validateUser (authHeader);
          if (user==null) {
            
            response.put("Status","Failed");
            response.put("Message", "UNAUTHORIZED USER");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            
          }
          
        
        return  bookingServics.deleteReservation(id);
      
       
    }

   

    
}
