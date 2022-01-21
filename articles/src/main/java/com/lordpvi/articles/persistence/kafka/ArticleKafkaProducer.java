package com.lordpvi.articles.persistence.kafka;

import com.lordpvi.articles.domain.Article;
import com.lordpvi.articles.infrastructure.ArticleMapper;
import com.lordpvi.articles.infrastructure.config.AppKafkaProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ArticleKafkaProducer {

    private final AppKafkaProperties appKafkaProperties;
    private final KafkaPublisher kafkaPublisher;

    public void produceCreateEvent(Article article) {
        final var createArticleDto = ArticleMapper.INSTANCE.toCreateArticleDto(article);
        final var metadata = new KafkaEvent.Metadata("1.0", "articles-api", "CreateArticleDto", LocalDateTime.now());
        kafkaPublisher.publish(appKafkaProperties.getArticleTopicName(), new KafkaEvent<>(metadata, createArticleDto));
    }

    public void produceViewEvent(Article article) {
        final var openArticleDto = ArticleMapper.INSTANCE.toOpenArticleDto(article, null);
        final var metadata = new KafkaEvent.Metadata("1.0", "articles-api", "OpenArticleDto", LocalDateTime.now());
        kafkaPublisher.publish(appKafkaProperties.getArticleMetricTopicName(), new KafkaEvent<>(metadata, openArticleDto));
    }

}
