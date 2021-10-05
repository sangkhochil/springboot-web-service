package com.sangkhochil.rest.webervices.restfulwebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sangkhochil.rest.webervices.restfulwebservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
