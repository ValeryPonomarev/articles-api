package com.lordpvi.articles.domain.factory;

import com.lordpvi.articles.domain.Article;
import com.lordpvi.articles.domain.kafka.KafkaArticleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper( ArticleMapper.class );

    com.lordpvi.articles.persistence.Article articleToPersistEntity(Article article);
    Article kafkaArticleDtoToArticle(KafkaArticleDto dto);
    KafkaArticleDto articleToKafkaArticleDto(Article dto);

}
