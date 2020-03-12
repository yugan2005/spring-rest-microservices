package com.home.restmicroservices.v1.dao;

import com.google.common.collect.ImmutableList;
import com.home.restmicroservices.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao implements Dao<Integer, User> {
  List<User> users = new ArrayList<>();

  @Override
  public Optional<User> getById(Integer id) {
    return users.stream().
        filter(user -> user.getId().equals(id)).findFirst();
  }

  @Override
  public List<User> getAll() {
    return ImmutableList.copyOf(users);
  }

  @Override
  public User save(User user) {
    if (user.getId() == null) {
      user.setId(users.size());
    }
    users.add(user);
    return user;
  }

  @Override
  public boolean deleteById(Integer id) {
    return users.removeIf(user -> user.getId().equals(id));
  }
}
