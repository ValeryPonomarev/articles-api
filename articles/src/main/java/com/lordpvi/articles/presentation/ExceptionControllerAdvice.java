package com.lordpvi.articles.presentation;

import com.lordpvi.articles.presentation.dto.ProblemDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionControllerAdvice {


    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
    private static final String APPLICATION_PROBLEM_JSON = "application/problem+json";

    @ExceptionHandler(Exception.class)
    public HttpEntity<ProblemDetail> handleException(Exception e,
                                                     final HttpServletRequest request) {

        LOGGER.error("An unexpected error occurred", e);

        ProblemDetail problem = new ProblemDetail("Internal Error", "An unexpected error has occurred");
        problem.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        problem.setInstance(request.getRequestURI());

        return new ResponseEntity<>(problem, overrideContentType(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpHeaders overrideContentType() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", APPLICATION_PROBLEM_JSON);
        return httpHeaders;
    }
}
