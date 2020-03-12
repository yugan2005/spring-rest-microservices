package com.home.restmicroservices.mapper;

import com.home.restmicroservices.dto.UserDto;
import com.home.restmicroservices.entity.User;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;


class UserMapperTest {
  private UserMapper userMapper;
  private User user;
  private UserDto userDto;

  @BeforeEach
  void setUp() {
    userMapper = Mappers.getMapper(UserMapper.class);
    String name = "test name";
    LocalDate birthDate = LocalDate.now();
    user = User.builder().name(name).birthDate(birthDate).build();
    userDto = UserDto.builder().name(name).birthDate(birthDate).build();
  }

  @Test
  void user2UserDto() {
    UserDto userDtoMapped = userMapper.user2UserDto(user);
    assertEquals(userDtoMapped.getName(), userDto.getName());
    assertEquals(userDtoMapped.getBirthDate(), userDto.getBirthDate());
  }

  @Test
  void userDto2User() {
    User userMapped = userMapper.userDto2User(userDto);
    assertEquals(userMapped.getBirthDate(), user.getBirthDate());
    assertEquals(userMapped.getName(), user.getName());
  }
}