package com.lordpvi.articles.domain.kafka;

import com.lordpvi.articles.domain.Article;
import com.lordpvi.articles.domain.factory.ArticleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ArticleKafkaProducer {

    //TODO move to configs
    private final String ARTICLES_TOPIC_NAME = "server.articles";
    private final KafkaTemplate<Long, KafkaArticleDto> kafkaTemplate;

    public void produce(Article article) {
        kafkaTemplate.send(ARTICLES_TOPIC_NAME, ArticleMapper.INSTANCE.articleToKafkaArticleDto(article));
    }

}
