package com.urlshorterner.urlshorterner.DT0;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class APIResponse<T> {
    private boolean succcess ;
    private String message ;
    private T data ;

}
