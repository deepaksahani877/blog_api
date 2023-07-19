package com.app.blog_api.exception;

public class ResourceNotFoundException extends RuntimeException{
    private final String resourceName;
    private final String fieldId;
    private final String fieldName;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldId){
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldId = fieldId;
    }

    @Override
    public String getMessage() {
        return String.format("%s not found with %s:%s",resourceName,fieldName,fieldId);
    }
}
