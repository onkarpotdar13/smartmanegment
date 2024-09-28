package com.scm.helper;

public class ResourceNotFoundException extends RuntimeException{

  public ResourceNotFoundException(String message)
  {
    super(message);
  }

  public ResourceNotFoundException(){
    super("RESOURCE NOT FOUND...");
  }

}
