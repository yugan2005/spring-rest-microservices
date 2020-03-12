package com.home.restmicroservices.mapper;

import com.home.restmicroservices.dto.UserDto;
import com.home.restmicroservices.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto user2UserDto(User user);

  User userDto2User(UserDto userDto);
}
