package com.home.restmicroservices.dto;

import java.time.LocalDate;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  private Integer id;

  @Size(min = 3, message = "name must at least be 3 characters long")
  private String name;

  @Past(message = "birthDate much be earlier than today")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDate;
}
