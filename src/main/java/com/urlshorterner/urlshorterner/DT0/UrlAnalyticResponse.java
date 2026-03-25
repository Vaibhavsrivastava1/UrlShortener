package com.urlshorterner.urlshorterner.DT0;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Builder
@Getter
@Setter
public class UrlAnalyticResponse {

    private String shortcode ;
    private String originalUrl ;
    private Long clicks ;


}
