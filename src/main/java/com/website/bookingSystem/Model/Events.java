package com.website.bookingSystem.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="events")
@Getter
@Setter
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public enum Category {
        MUSIC,
        TECH,
        SPORTS,
        ART,
        EDUCATION,
        BUSINESS
    }
     @Enumerated(EnumType.STRING) // Save the enum as a String in the database
    private Category category ;
    
    

    @Column(nullable = false, unique = true)
    private String eventName;

    @Column(nullable = false, length =255)
    private String description;

    @Column(nullable = false)
    private String venue;


    @Column(nullable = false)
    private LocalDate  date;

    @Column(nullable = false, length =255)
    private Double price ;
    @Column(nullable = false)
    private String imageUrl ;

    public Events (){}
    public Events (String evnetName, String description, LocalDate   date , Category category , Double price ,String venue, String imageUrl)
    { this.eventName=evnetName;
        this.description=description;
        this.date=date;
        this.category=category;
        this.price=price;
        this.venue=venue;
        this.imageUrl=imageUrl;

    }







    



    
}
