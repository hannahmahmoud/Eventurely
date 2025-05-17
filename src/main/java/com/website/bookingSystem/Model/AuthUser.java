package com.website.bookingSystem.Model;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity // telling spring boot this file is going to generate a table 
@Table(name = "user") // creating table in database called user

//validation in the validation folder msh hena khody balek shali el validation el 3melha hena 
public class AuthUser {
    // @Id: Marks the primary key.
    //@GeneratedValue: Auto-generates the ID
    //@Column: Maps the field to a database column (with optional constraints).
    // dah ashan ykon two types bs leh role 
    
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,length=15)
    private String phoneNumber;

    @Column(nullable=false, length = 255)
    private String firstName;

    @Column(nullable=false,length = 255)
    private String lastName;

    @Column(nullable = false,length = 255)
    private String password;



    @Column(nullable= false, unique = true,length = 100)
    private String email;

    @Column(nullable = true)
    private Boolean active;

    @Column(nullable= false)
    private Boolean isAdmin;


    // used to set a deafult values
    @PrePersist
   public void setDefaultRole() {
    if (this.isAdmin == null) {
        this.isAdmin=false;
    }
}
    @Column
    private Date passwordChangedAt;
    @Column
    private String  resetPasswordToken;
    @Column
    private Date resetPasswordTokenExpire;


    // Constructors
    public AuthUser() {}

    public AuthUser(String firstName, String lastName, String email, String password, String phoneNumber, Boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber=phoneNumber;
        this.isAdmin=isAdmin;
    }


   
   

   
    
    
}
