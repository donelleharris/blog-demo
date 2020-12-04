package com.donelle_harris.blog.models;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String path;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Image(Long id, String path) {
        this.id = id;
        this.path = path;
    }
    public Image(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
}
