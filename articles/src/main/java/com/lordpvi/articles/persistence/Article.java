package com.lordpvi.articles.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {

    @Id
    @SequenceGenerator(
        name = "article_id_sequence",
        sequenceName = "article_id_sequence"
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "article_id_sequence"
    )
    private Integer id;
    private String title;
    private String description;
    private String content;

}
