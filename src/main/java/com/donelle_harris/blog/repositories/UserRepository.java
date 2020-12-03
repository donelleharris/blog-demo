package com.donelle_harris.blog.repositories;

import com.donelle_harris.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);//select * from users where username = ?
    User findById(long id);//select * from users where id = ?
}
