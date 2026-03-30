package com.urlshorterner.urlshorterner.DT0;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "NAME cannot be empty")
    private String name ;

     @Email(message = "Eamil is Required")
    private String email;
     
    @NotBlank(message = "Password is Required")
    private String password;
   

}
