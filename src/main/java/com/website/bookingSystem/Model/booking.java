package com.website.bookingSystem.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="booking")
@Getter
@Setter
@NoArgsConstructor
public class booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
   private Long userId;

   @Column(nullable = false)
   private Long eventId;
   @Column(nullable=false )
   private LocalDateTime timeStamp;
   public booking(Long userID, Long eventId, LocalDateTime   timeStamp)
   {
this.eventId=eventId;
this.userId=userID;
this.timeStamp=timeStamp;
   }
   


    
}
