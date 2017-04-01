package com.alex.dal;

import com.alex.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {


     User findByUserName(String userName);

}

