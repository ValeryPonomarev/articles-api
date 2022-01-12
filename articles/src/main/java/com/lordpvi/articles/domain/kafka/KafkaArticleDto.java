package com.lordpvi.articles.domain.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaArticleDto implements KafkaDto {
    private String title;
    private String description;
    private String content;
}
