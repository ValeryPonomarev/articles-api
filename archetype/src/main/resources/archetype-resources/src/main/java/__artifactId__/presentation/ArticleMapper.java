package com.lordpvi.${artifactId}.presentation;

import com.lordpvi.${artifactId}.domain.Article;
import com.lordpvi.${artifactId}.presentation.dto.ArticleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper( ArticleMapper.class );

    Article articleRequestToArticle(ArticleRequest dto);

}
