package com.lordpvi.articles.domain;

import com.lordpvi.articles.infrastructure.ArticleMapper;
import com.lordpvi.articles.persistence.kafka.ArticleKafkaProducer;
import com.lordpvi.articles.persistence.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleKafkaProducer articleKafkaProducer;

    public void create(Article article) {
        articleRepository.save(ArticleMapper.INSTANCE.toPersistEntity(article));
    }

    public Article openArticle(UUID articleId) {
        final var article = ArticleMapper.INSTANCE.toArticle(articleRepository.getById(articleId));
        articleKafkaProducer.produceCreateEvent(article);
        return article;
    }

}
