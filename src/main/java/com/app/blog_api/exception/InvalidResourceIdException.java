package com.app.blog_api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidResourceIdException extends RuntimeException{

    private final String message;
    private final Long postId;

    public InvalidResourceIdException(String message, Long postId){
        this.message = message;
        this.postId = postId;
    }

    @Override
    public String getMessage() {
        return String.format("%s :<Required valid resource Id but found %s>",message,postId);
    }
}
