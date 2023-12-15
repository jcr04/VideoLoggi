package com.projetos.VideoLoggi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "videos")
@Getter @Setter @NoArgsConstructor @ToString
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date uploadDate;

    public Video(String title, String description, String url, Date uploadDate) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.uploadDate = uploadDate;
    }
}
