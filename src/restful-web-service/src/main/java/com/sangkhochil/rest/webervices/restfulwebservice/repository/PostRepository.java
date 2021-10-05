package com.sangkhochil.rest.webervices.restfulwebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sangkhochil.rest.webervices.restfulwebservice.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
