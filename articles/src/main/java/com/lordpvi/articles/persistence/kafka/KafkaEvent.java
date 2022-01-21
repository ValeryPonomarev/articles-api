package com.lordpvi.articles.persistence.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaEvent<T> implements Serializable {

    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("payload")
    private T payload;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Metadata implements Serializable {

        @JsonProperty("eventVersion")
        private String eventVersion;
        @JsonProperty("eventSource")
        private String eventSource;
        @JsonProperty("eventName")
        private String eventName;
        @JsonProperty("eventTime")
        private LocalDateTime eventTime;

    }
}
