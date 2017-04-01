package com.alex.laptop;

import com.alex.dal.UserRepository;
import com.alex.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;


    //@Query("select b from User b where b.userName= :userName")
    public User getUserByUserName(@Param("userName") String userName) {
        User user = repository.findByUserName(userName);
        return user;
  }

}
