package com.website.bookingSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.website.bookingSystem.Model.Events;

import java.util.List;



@Repository
public interface eventRepo extends JpaRepository<Events,Long>
 {
 
    boolean existsByeventName(String eventName);
   List<Events> findByEventName(String eventName);
   Optional<Events> deleteByeventName(String eventName);
   
 

}
