package com.lordpvi.articles.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private UUID id;
    private String title;
    private String description;
    private String content;
}
