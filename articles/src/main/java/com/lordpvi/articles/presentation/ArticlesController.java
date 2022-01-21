package com.lordpvi.articles.presentation;

import com.lordpvi.articles.domain.ArticleService;
import com.lordpvi.articles.infrastructure.ArticleMapper;
import com.lordpvi.articles.persistence.kafka.ArticleKafkaProducer;
import com.lordpvi.articles.presentation.dto.ArticleRequest;
import com.lordpvi.articles.presentation.dto.ArticleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RestController
@RequestMapping("api/v1/articles")
public class ArticlesController {

    private final ArticleService articleService;
    private final ArticleKafkaProducer articleKafkaProducer;

    @GetMapping("{articleId}")
    public ResponseEntity<ArticleResponse> openArticle(@PathVariable UUID articleId) {
        final var article = articleService.openArticle(articleId);
        return ResponseEntity.ok(ArticleMapper.INSTANCE.toArticleResponse(article));
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequest request) {
        log.info("Creating new article {}", request);
        articleKafkaProducer.produceCreateEvent(ArticleMapper.INSTANCE.toArticle(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
