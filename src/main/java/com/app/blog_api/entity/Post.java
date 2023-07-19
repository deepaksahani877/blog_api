package com.app.blog_api.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_title", nullable = false, unique = true)
    private String title;

    @Column(name = "post_url", nullable = false, unique = true)
    private String url;

    @Column(nullable = false)
    private String shortDescription;

    @Column(nullable = false, length = Integer.MAX_VALUE)
    private String content;

    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updatedOn;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
