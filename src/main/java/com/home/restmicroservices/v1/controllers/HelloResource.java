package com.home.restmicroservices.v1.controllers;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloResource {
  private static final String HELLO_MESSAGE_NAME = "hello.message";
  private final MessageSource messageSource;

  public HelloResource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @GetMapping(path = "/hello-i18n")
  public String helloI18n() {
    return messageSource.getMessage(HELLO_MESSAGE_NAME, null, LocaleContextHolder.getLocale());
  }
}
