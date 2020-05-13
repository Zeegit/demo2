package ru.zeet.demo2.domain;

import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "image")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Lob
    private Byte[] image;

    public Image() {
    }

    public Image(String description, Byte[] image) {
        this.description = description;
        this.image = image;
    }
}
