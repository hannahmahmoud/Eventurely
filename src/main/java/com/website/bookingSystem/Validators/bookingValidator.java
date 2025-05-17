package com.website.bookingSystem.Validators;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class bookingValidator {

    @NotBlank(message ="USER ID IS REQUIRED FIELD!")
    private Long userId;
     @NotBlank(message ="USER ID IS REQUIRED FIELD!")
    private Long eventId;


    
}
