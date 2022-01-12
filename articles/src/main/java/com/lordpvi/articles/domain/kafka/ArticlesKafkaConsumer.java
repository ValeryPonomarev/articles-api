package com.lordpvi.articles.domain.kafka;

import com.lordpvi.articles.domain.ArticleService;
import com.lordpvi.articles.domain.factory.ArticleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ArticlesKafkaConsumer {

    private final ArticleService articleService;

    @KafkaListener(id = "articles", topics = {"server.articles"}, containerFactory = "singleFactory")
    public void consume(KafkaArticleDto kafkaArticleDto) {
        log.info("consumed new kafka message: {}", kafkaArticleDto);
        articleService.create(ArticleMapper.INSTANCE.kafkaArticleDtoToArticle(kafkaArticleDto));
    }

}
