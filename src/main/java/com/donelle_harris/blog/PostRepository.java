package com.donelle_harris.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title); //select * from posts where title = ?
    Post findFirstByTitle(String title);//select * from posts where title = ? limit 1
    Post getPostById(long id);
    @Query("from Post a where a.title like %:term%")
    List<Post> searchByTitleLike(@Param("term") String term);
}
