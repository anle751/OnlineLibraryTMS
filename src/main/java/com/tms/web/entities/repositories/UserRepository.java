package com.tms.web.entities.repositories;

import com.tms.web.entities.security.User.User;
import com.tms.web.entities.security.User.projections.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsername(String username);
    public UserInfo getUserById(Long id);
}
