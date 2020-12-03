package com.donelle_harris.blog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;

    public Tag() {}
    //read
    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }
    //create
    public Tag(String name) {
        this.name= name;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getPosts() {
        return posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
