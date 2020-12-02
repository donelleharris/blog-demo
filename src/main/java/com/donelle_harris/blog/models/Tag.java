package com.donelle_harris.blog.models;

import javax.persistence.*;

@Entity
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100)
    private String name;
//    @ManyToOne
//    @JoinColumn (name = "post_category")
//    private Post post;

    public Tag() {

}

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
