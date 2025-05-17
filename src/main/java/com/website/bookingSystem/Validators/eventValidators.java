package com.website.bookingSystem.Validators;
import java.time.LocalDate;

import com.website.bookingSystem.Model.Events.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class eventValidators {
    private Long id; 
    @NotBlank(message = "Event Name is a required Field!")
    private String eventName;
   
    
    @NotBlank(message="Description is a required field!")
    private String description;

    
    @NotNull(message="Category is a required field!")
    private Category category;

   

    @NotBlank(message="Venue is a required field!")
    private String venue;

    @NotNull(message="Date is required field!")
    private LocalDate  date ;

    @NotBlank(message="Image Url  is a required field!")
    private String imageUrl;

    @NotNull(message="Price  is a required field!")
    @PositiveOrZero(message = "Price must be zero or positive")
    private Double price;
    eventValidators(String eventName, String description , Category category, String venue, LocalDate  date, String imageUrl, double Price )
    { 
        this.eventName=eventName;
        this.description=description;
        this.category=category;
        this.venue=venue;
        this.date=date;
        this.imageUrl=imageUrl;
        this.price=price;


    }




    
}
