package com.home.restmicroservices.v1.dao;

import java.util.List;
import java.util.Optional;


public interface Dao<ID, T> {
  Optional<T> getById(ID id);

  List<T> getAll();

  T save(T t);

  boolean deleteById(ID id);
}
