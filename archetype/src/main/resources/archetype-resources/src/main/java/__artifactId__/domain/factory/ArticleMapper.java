package com.lordpvi.${artifactId}.domain.factory;

import com.lordpvi.${artifactId}.domain.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper( ArticleMapper.class );

    com.lordpvi.${artifactId}.persistence.Article articleToPersistEntity(Article article);

}
