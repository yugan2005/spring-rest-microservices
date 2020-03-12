package com.home.restmicroservices.exceptions;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseException {
  private Date timeStamp;
  private String message;
  private String description;
}
