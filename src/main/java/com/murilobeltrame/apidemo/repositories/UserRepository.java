package com.murilobeltrame.apidemo.repositories;

import com.murilobeltrame.apidemo.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> { }
