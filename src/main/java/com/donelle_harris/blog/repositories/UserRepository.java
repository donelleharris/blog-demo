package com.donelle_harris.blog.repositories;


import com.donelle_harris.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User findAllByLastNameIsLike(String term);

    User findByUsername(String testUser);
}
