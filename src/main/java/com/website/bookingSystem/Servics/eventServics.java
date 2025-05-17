package com.website.bookingSystem.Servics;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.website.bookingSystem.Model.*;
import com.website.bookingSystem.Repository.eventRepo;
import com.website.bookingSystem.Validators.eventValidators;
import com.website.bookingSystem.Validators.updateRecordDTO;

import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;



 @Service
public class eventServics {

    @Autowired
    private eventRepo eventRepo;
    private Events event;


// desc: func to post new event 
//route: post request
    public ResponseEntity<Object> postEvent (eventValidators newEvent)
 {    Map<String, Object> response = new HashMap<>();
  boolean oldEvent = eventRepo.existsByeventName(newEvent.getEventName());
  if (oldEvent==true)
  {
    response.put("status", "Failed");
    response.put("message", "Event already exists.");
    return ResponseEntity
    .status(HttpStatus.FORBIDDEN)
         .body(response);

  }
  event =  new Events(newEvent.getEventName(),newEvent.getDescription(),newEvent.getDate(),newEvent.getCategory(),newEvent.getPrice(),newEvent.getVenue(),newEvent.getImageUrl());
  
  
  eventRepo.save(event);

     response.put("status", "Sucess");
     response.put("Event: ", event);
     return ResponseEntity
     .status(HttpStatus.CREATED)
          .body(response);
 }

// desc: func to delete by id  
//route: delete //{id}
public ResponseEntity <Object> deleteEvent (Long id)
{Map<String, Object> response = new HashMap<>();
     boolean exitsEvent= eventRepo.existsById(id);
    if (!exitsEvent)
    {
        response.put("status", "Failed");
    response.put("message", "Event is NOTFOUND");
    return ResponseEntity
    .status(HttpStatus.NOT_FOUND)
         .body(response);
    }
    eventRepo.deleteById(id);
    return ResponseEntity.noContent().build(); 

}

//desc: func to get by id 
//route : get//{id}
public ResponseEntity <Object> getEvent (Long id)
{
    Map<String, Object> response = new HashMap<>();
    boolean exitsEvent= eventRepo.existsById(id);
    if (!exitsEvent)
    {
        response.put("Status: ","Failed");
        response.put ("Messgae: ", "Event is NOTFOUND!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(response);
    }
   Optional <Events> event= eventRepo.findById(id);
   response.put("Status: ","Sucess");
   response.put ("Event: ", event);
   return ResponseEntity.status(HttpStatus.OK)
   .body(response);

}

//desc: func to get by eventName 
//route : get//{name}
public ResponseEntity <Object> getEventByName (String name)
{
    Map<String, Object> response = new HashMap<>();
    boolean exitsEvent= eventRepo.existsByeventName(name);
    if (!exitsEvent)
    {
        response.put("Status: ","Failed");
        response.put ("Messgae: ", "Event is NOTFOUND!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(response);
    }
    List<Events>  event= eventRepo.findByEventName(name);
   response.put("Status: ","Sucess");
   response.put ("Event: ", event);
   return ResponseEntity.status(HttpStatus.OK)
   .body(response);

}

//desc: function to update the attributes in db 
// route: put

public ResponseEntity<Object>updateRecords(Long id,updateRecordDTO updatedEvent )
{ int nonNullfields=0;
     Map<String, Object> response = new HashMap<>();
    boolean exitsEvent=eventRepo.existsById(id);
    if (!exitsEvent)
    {
        response.put("Status:", "Failed");
        response.put("Message:", "ID is NOTFOUND!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(response);
    }
    Optional<Events> optionalEvent = eventRepo.findById(id);
    event= optionalEvent.get();


    
 if (updatedEvent.getEventName()!=null)
  {nonNullfields+=1;
    event.setEventName(updatedEvent.getEventName());

}
 if (updatedEvent.getCategory()!=null)
   { nonNullfields+=1;
    event.setCategory(updatedEvent.getCategory());
    }
 if (updatedEvent.getDate()!=null)
 {    if (updatedEvent.getDate().isBefore(LocalDate.now()))
        { response.put("Status:", "Failed");
        response.put("Message:", "Invalid Date!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(response);}
nonNullfields+=1;
    event.setDate(updatedEvent.getDate());
}
     if (updatedEvent.getDescription()!= null){
    event.setDescription(updatedEvent.getDescription());
nonNullfields+=1;}
     if (updatedEvent.getVenue()!=null){
    event.setVenue(updatedEvent.getVenue());
nonNullfields+=1;}
     if (updatedEvent.getPrice()!=null)
     { 
        if( updatedEvent.getPrice()<0)
        {
             response.put("Status:", "Failed");
        response.put("Message:", "Invalid Price!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(response);
        }
    event.setPrice(updatedEvent.getPrice());
    nonNullfields+=1;
}

     if (updatedEvent.getImage_URL()!=null){
    event.setImageUrl(updatedEvent.getImage_URL());
     nonNullfields+=1;
    }
    if (nonNullfields==0)
    {
         response.put("Status:", "Failed");
        response.put("Message:", "No  in the  data provided ");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(response); 
    }
eventRepo.save(event);
response.put("Status: ","Success");
response.put ("Updated Event: ",event);
return ResponseEntity.status(HttpStatus.OK).body(response);

}

public ResponseEntity<Object> getAllEvents() {
   Pageable pageable = PageRequest.of(0, 10, Sort.by("date").descending());
     Page<Events> eventsPage = eventRepo.findAll(pageable);
     Map<String, Object> response = new HashMap<>();
     response.put("Status: ","Sucess");
     response.put("Events: ",eventsPage);
     return ResponseEntity.status(HttpStatus.OK)
     .body(response);
     




}



}
