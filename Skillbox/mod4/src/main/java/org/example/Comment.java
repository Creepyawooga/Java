package org.example;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Comment content is required")
    private String content;

    @ManyToOne
    private User author;

    @ManyToOne
    private News news;

}