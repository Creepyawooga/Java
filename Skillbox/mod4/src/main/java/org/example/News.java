package org.example;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    @ManyToOne
    private User author;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private List<Comment> comments;


}