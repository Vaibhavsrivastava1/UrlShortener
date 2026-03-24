package com.urlshorterner.urlshorterner.DT0;



import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlRequest {

    @NotBlank(message = " Url Cannot be Empty")
    @URL(message = "Invalid URL")
    private String Url ;

}
