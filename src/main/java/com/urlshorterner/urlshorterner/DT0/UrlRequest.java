package com.urlshorterner.urlshorterner.DT0;



import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,10}$",
         message = "Invalid custom code")

    private String customcode ;

}
