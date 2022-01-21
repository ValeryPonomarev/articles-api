package com.lordpvi.articles.persistence.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lordpvi.articles.domain.ArticleService;
import com.lordpvi.articles.infrastructure.ArticleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.KafkaMessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ArticlesKafkaConsumer {

    private final ArticleService articleService;
    private final ObjectMapper objectMapper;

    @KafkaListener(id = "${spring.kafka.consumer.group-id}", topics = {"${app.kafka.article-topic-name}"})
    public void consume(@Payload String message, @Headers KafkaMessageHeaders headers) {
        log.info("Received new article event from Kafka, payload: {}, header: {}", message, headers);
        final CreateArticleDto createArticleDto = unmarshal(message, new TypeReference<KafkaEvent<CreateArticleDto>>() {}).getPayload();
        articleService.create(ArticleMapper.INSTANCE.toArticle(createArticleDto));
    }

    public <T> T unmarshal(String message, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(message, typeReference);
        } catch (IOException e) {
            log.error("Failed to deserialize incoming message: {}", message);
            throw new RuntimeException("Failed to deserialize incoming message", e);
        }
    }

}
