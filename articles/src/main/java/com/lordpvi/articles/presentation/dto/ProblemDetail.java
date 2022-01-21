package com.lordpvi.articles.presentation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * <pre  class="code">
 * {
 * "type": "https://adidas-group.com/problems/scv/unauthorized",
 * "title": "Authentication required",
 * "detail": "Missing authentication credentials for the Greeting resource.",
 * "instance": "/greeting",
 * "status": 401
 * }
 * </pre>
 *
 * @author Bogdan Gromiuk
 * @author Volodymyr Martynyak
 */
@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProblemDetail {

    public ProblemDetail(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    private String title;

    private String detail;

    private String type;

    private String instance;

    private Integer status;

    private List<ProblemDetail> errors;

}
