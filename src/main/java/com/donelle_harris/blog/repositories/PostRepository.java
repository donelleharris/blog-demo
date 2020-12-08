package com.donelle_harris.blog.repositories;

import com.donelle_harris.blog.models.Post;
import com.donelle_harris.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title); //select * from posts where title = ?
    Post findFirstByTitle(String title);//select * from posts where title = ? limit 1
    Post getPostById(long id);
    Post deleteById(long id);
    Post deleteByTitle(String title);
    List<Post> findAllByTitleIsLike(String term);
    List<Post> findAllByUser(User user);
    List<Post> deleteAllByUserId(Long id);//delete * from posts where user_id = ?

}
