package com.home.restmicroservices.v1.services;

import com.home.restmicroservices.dto.UserDto;
import com.home.restmicroservices.exceptions.NoUserFoundException;
import com.home.restmicroservices.mapper.UserMapper;
import com.home.restmicroservices.v1.dao.UserDao;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;


@Service
public class UserService {
  private final UserDao userDao;
  private final UserMapper userMapper;

  public UserService(UserDao userDao, UserMapper userMapper) {
    this.userDao = userDao;
    this.userMapper = userMapper;
  }

  public UserDto getById(int id) {
    return userMapper.user2UserDto(
        userDao.getById(id).orElseThrow(() -> new NoUserFoundException("User by ID: " + id + " does not exist")));
  }

  public List<UserDto> getAll() {
    return userDao.getAll().stream().map(userMapper::user2UserDto).collect(Collectors.toList());
  }

  public UserDto save(UserDto userDto) {
    return userMapper.user2UserDto(userDao.save(userMapper.userDto2User(userDto)));
  }

  public boolean deleteById(int id) {
    return userDao.deleteById(id);
  }
}
