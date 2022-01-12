package com.lordpvi.articles.presentation;

import com.lordpvi.articles.domain.Article;
import com.lordpvi.articles.presentation.dto.ArticleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper( ArticleMapper.class );

    Article articleRequestToArticle(ArticleRequest dto);

}
