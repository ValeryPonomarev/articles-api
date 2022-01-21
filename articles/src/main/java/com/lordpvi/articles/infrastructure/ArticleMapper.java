package com.lordpvi.articles.infrastructure;

import com.lordpvi.articles.domain.Article;
import com.lordpvi.articles.persistence.kafka.CreateArticleDto;
import com.lordpvi.articles.presentation.dto.ArticleRequest;
import com.lordpvi.articles.presentation.dto.ArticleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper( ArticleMapper.class );

    Article toArticle(com.lordpvi.articles.persistence.Article article);
    @Mapping(target = "article.id", ignore = true)
    Article toArticle(CreateArticleDto dto);
    @Mapping(target = "article.id", ignore = true)
    Article toArticle(ArticleRequest dto);

    @Mapping(target = "article.id", ignore = true)
    com.lordpvi.articles.persistence.Article toPersistEntity(Article article);
    com.lordpvi.articles.persistence.kafka.CreateArticleDto toCreateArticleDto(Article article);
    @Mapping(target = "articleId", expression = "java( String.valueOf(article.getId()) )")
    com.lordpvi.articles.persistence.kafka.OpenArticleDto toOpenArticleDto(Article article, String email);
    ArticleResponse toArticleResponse(Article article);
}
