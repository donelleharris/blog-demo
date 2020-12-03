package com.donelle_harris.blog.repos;

import com.donelle_harris.blog.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Author, Long> {
    Author findByUsername(String username);//select * from users where username = ?
    Author findById(long id);//select * from users where id = ?

}
