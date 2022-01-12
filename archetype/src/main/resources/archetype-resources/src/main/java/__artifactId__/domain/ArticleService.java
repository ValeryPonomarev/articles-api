package com.lordpvi.${artifactId}.domain;

import com.lordpvi.${artifactId}.domain.factory.ArticleMapper;
import com.lordpvi.${artifactId}.persistence.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void create(Article article) {
        articleRepository.save(ArticleMapper.INSTANCE.articleToPersistEntity(article));
    }

}
