package com.lordpvi.articles.presentation;

import com.lordpvi.articles.domain.kafka.ArticleKafkaProducer;
import com.lordpvi.articles.presentation.dto.ArticleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RestController
@RequestMapping("api/v1/articles")
public class ArticlesController {

    private final ArticleKafkaProducer articleKafkaProducer;

    @PostMapping
    public void createArticle(@RequestBody ArticleRequest request) {
        log.info("Creating new article {}", request);
        articleKafkaProducer.produce(ArticleMapper.INSTANCE.articleRequestToArticle(request));
    }

}
