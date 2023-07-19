package com.app.blog_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private String errorMessage;
    private String description;
    private String statusCode;
    private Date timestamp;


}
