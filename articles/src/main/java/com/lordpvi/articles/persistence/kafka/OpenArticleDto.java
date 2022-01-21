package com.lordpvi.articles.persistence.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenArticleDto {
    @JsonProperty("articleId")
    private String articleId;
    @JsonProperty("email")
    private String email;
}
