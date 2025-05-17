package com.website.bookingSystem.Validators;
import java.time.LocalDate;

import com.website.bookingSystem.Model.Events.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class updateRecordDTO {

 private Long id;   
private  String eventName;
private  String description;  
private  String image_URL;   
private  String venue; 
private LocalDate  date;   
private Double price;  
private Category category;
}
