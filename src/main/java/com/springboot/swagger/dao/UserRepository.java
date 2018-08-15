package com.springboot.swagger.dao;

import com.springboot.swagger.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    List<User> findAll();

    User findUserById( long id);

    @Override
    Page<User> findAll(Pageable pageable);
}
