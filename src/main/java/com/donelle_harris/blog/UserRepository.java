package com.donelle_harris.blog;
import com.donelle_harris.blog.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);//Select from users where username = ?
    User findFirstByUsername(String username); //Select from posts where userId = ???? not sure
    User getUserById(long id);
//    @Query("from Post a where a.userId like %:term%")
//    List<User> searchByUsernameLike(@Param("term") String term);
}
