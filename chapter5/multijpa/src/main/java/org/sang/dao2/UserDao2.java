package org.sang.dao2;

import org.sang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao2 extends JpaRepository<User,Integer> {
}
