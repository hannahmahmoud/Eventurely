package com.website.bookingSystem.Servics;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.website.bookingSystem.Model.AuthUser;
import com.website.bookingSystem.Repository.userRepo;
import com.website.bookingSystem.Response.ResponseMessage;
import com.website.bookingSystem.Validators.loginValidator;
import com.website.bookingSystem.Validators.signupValidator;
import com.website.bookingSystem.config.jwtUtil;

import io.jsonwebtoken.JwtException;


@Service

public class userServics {


  private ResponseMessage message;
  
    
    @Autowired
private  userRepo userRepository;

@Autowired
    private PasswordEncoder  passwordEncoder; 
  
  @Autowired
  private jwtUtil jwtUtil;
private AuthUser authUser;
 public ResponseEntity<Object> signup (signupValidator newUser)
 {    Map<String, Object> response = new HashMap<>();
  boolean oldUser = userRepository.existsByEmail(newUser.getEmail());
  if (oldUser==true)
  {
    response.put("Status","Failed");
  response.put("Message"," Email already exits" );
    return new ResponseEntity<>(message,HttpStatus.FORBIDDEN);
  }
  
  authUser = new AuthUser(
        newUser.getFirstName(),
        newUser.getLastName(),
        newUser.getEmail(),
        this.passwordEncoder.encode (newUser.getPassword()),
        newUser.getPhoneNumber(),
        newUser.getIsAdmin()

     );
     userRepository.save(authUser);
     newUser.setPassword(null);


     ResponseMessage message = new ResponseMessage();
    message.setStatus("Success");
    message.setUser(newUser);

      return new ResponseEntity<>(message, HttpStatus.CREATED); 
 }

public ResponseEntity<Object> login(loginValidator user)

{
    Map<String, Object> response = new HashMap<>();

  authUser= userRepository.findByEmail(user.getEmail()).orElse(null);
  if (authUser== null )
  {
  response.put("Status","Failed");
  response.put("Message","Incorrect Email" );
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }
  String password= user.getPassword();
  String hashedPassword= authUser.getPassword();
  if (!passwordEncoder.matches(password, hashedPassword))
  {
    response.put("Status","Failed");
  response.put("Message","Incorrect Password" );
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  
  }
  // matnsesh t3mli hwar token dah mohem 
  String token =jwtUtil.generateToken(user.getEmail());
   response.put("Status: ","Success");
   response.put("token",token);
   response.put("isAdmin",authUser.getIsAdmin());

  return ResponseEntity.status(HttpStatus.OK).body(response);

}

public boolean isAdmin(AuthUser user) {
    return user != null  && user.getIsAdmin();
}


public AuthUser validateUser(String authHeader )
{
  if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

    String token = authHeader.replace("Bearer","" ).trim();
       if (!jwtUtil.validateToken(token)) {
            return null;
        }

        String email;
        try {
            email = jwtUtil.extractEmail(token);
        } catch (JwtException e) {
            return null;
        }

    return  userRepository.findByEmail(email).orElse(null);

}


     
}
    

