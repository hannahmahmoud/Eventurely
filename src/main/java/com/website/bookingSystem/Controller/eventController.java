package com.website.bookingSystem.Controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.bookingSystem.Model.AuthUser;
import com.website.bookingSystem.Servics.eventServics;
import com.website.bookingSystem.Servics.userServics;
import com.website.bookingSystem.Validators.eventValidators;
import com.website.bookingSystem.Validators.updateRecordDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/v1/events")

public class eventController {
    @Autowired 
    private eventServics eventServics;
    @Autowired
    private userServics userServics;


    @GetMapping("/")
    public ResponseEntity<Object> getAllEvents() {
        return eventServics.getAllEvents();}



    @PostMapping( value = "/newEvent", consumes = "application/json", produces = "application/json")// ashan kda asmha post mapping 
    public ResponseEntity<Object> newEvent(@RequestHeader ("Authorization") String authHeader, @RequestBody eventValidators newEvent) {
          Map<String, Object> response = new HashMap<>();
        AuthUser user= userServics.validateUser (authHeader);
          if (user==null) {
            
            response.put("Status","Failed");
            response.put("Message", "UNAUTHORIZED USER");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            
          }
          Boolean authorizationCheck= userServics.isAdmin(user); 
          if (authorizationCheck==false)
          {
            
            response.put("Status","Failed");
            response.put("Message", "Forbidden: You are not allowed to perform this action");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            
          }
 
        return  eventServics.postEvent(newEvent);
       
    }
    @DeleteMapping( value = "/deleteEvent/{id}")
    public ResponseEntity<Object> deleteEvent(@RequestHeader ("Authorization") String authHeader,@PathVariable Long id) {
        //TODO: process delete request
          Map<String, Object> response = new HashMap<>();
        AuthUser user= userServics.validateUser (authHeader);
          if (user==null) {
            
            response.put("Status","Failed");
            response.put("Message", "UNAUTHORIZED USER");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            
          }
          Boolean authorizationCheck= userServics.isAdmin(user); 
          if (authorizationCheck==false)
          {
            
            response.put("Status","Failed");
            response.put("Message", "Forbidden: You are not allowed to perform this action");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            
          }
        return  eventServics.deleteEvent(id);
       
    }
    @GetMapping("/getEvent/{id}")
    public ResponseEntity<Object>  getEvent(@RequestHeader ("Authorization") String authHeader,@PathVariable Long id) {
        //TODO: process get request
          Map<String, Object> response = new HashMap<>();
        AuthUser user= userServics.validateUser (authHeader);
          if (user==null) {
            
            response.put("Status","Failed");
            response.put("Message", "UNAUTHORIZED USER");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            
          }
      
        return eventServics.getEvent(id);
    }

 
    @GetMapping("/search")
    public ResponseEntity<Object> searchEvents(
      @RequestHeader("Authorization") String authHeader,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name) {
    
              AuthUser user= userServics.validateUser (authHeader);
          if (user==null) {
             Map<String, Object> response = new HashMap<>();
            response.put("Status","Failed");
            response.put("Message", "UNAUTHORIZED USER");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            
          }
      
        if (id != null) {
            return eventServics.getEvent(id);
        } else if (name != null && !name.isEmpty()) {
            return eventServics.getEventByName(name);
        } else {
            return eventServics.getAllEvents();
        }

      
    }
     @PutMapping("updateEvent/{id}")
     public ResponseEntity<Object> updateEvent(@RequestHeader("Authorization") String authHeader,@PathVariable Long id, @RequestBody updateRecordDTO updatedEvent) {
         //TODO: process PUT request
                   Map<String, Object> response = new HashMap<>();
        AuthUser user= userServics.validateUser (authHeader);
          if (user==null) {
            
            response.put("Status","Failed");
            response.put("Message", "UNAUTHORIZED USER");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            
          }
          Boolean authorizationCheck= userServics.isAdmin(user); 
          if (authorizationCheck==false)
          {
            
            response.put("Status","Failed");
            response.put("Message", "Forbidden: You are not allowed to perform this action");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            
          }
         
         return eventServics.updateRecords(id, updatedEvent);
     }
           
        
    }
    
    
    


    

