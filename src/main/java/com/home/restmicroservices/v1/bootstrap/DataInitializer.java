package com.home.restmicroservices.v1.bootstrap;

import com.home.restmicroservices.entity.User;
import com.home.restmicroservices.v1.dao.UserDao;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {

  private final UserDao useDao;

  public DataInitializer(UserDao useDao) {
    this.useDao = useDao;
  }

  @Override
  public void run(String... args) throws Exception {
    loadUsers();
  }

  private void loadUsers() {
    User ross = User.builder().name("Ross").birthDate(LocalDate.of(1978, 9, 10)).build();
    User tiffany = User.builder().name("Tiffany").birthDate(LocalDate.of(2009, 4, 2)).build();
    useDao.save(ross);
    useDao.save(tiffany);
  }
}
