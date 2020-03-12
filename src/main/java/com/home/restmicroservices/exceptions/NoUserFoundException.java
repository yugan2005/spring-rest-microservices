package com.home.restmicroservices.exceptions;

import java.util.NoSuchElementException;


public class NoUserFoundException extends NoSuchElementException {
  public NoUserFoundException(String message) {
    super(message);
  }
}
