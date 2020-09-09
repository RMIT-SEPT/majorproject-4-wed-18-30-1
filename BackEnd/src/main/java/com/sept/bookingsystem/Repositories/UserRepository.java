package com.sept.bookingsystem.Repositories;

import com.sept.bookingsystem.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    @Override
    Iterable<User> findAll();
}
