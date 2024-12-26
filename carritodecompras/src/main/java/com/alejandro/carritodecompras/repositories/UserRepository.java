package com.alejandro.carritodecompras.repositories;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.carritodecompras.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
