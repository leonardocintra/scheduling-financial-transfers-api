package com.leaolabs.scheduling.commons.controller;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;
import java.util.List;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class ResponseMeta implements Serializable {
    private Meta meta;
    private List<?> records;
    private List<ErrorMessage> errorMessages;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @ToString
    @Builder
    public static class Meta implements Serializable {

        private static final long serialVersionUID = -3992980364349941426L;

        private String version;
        private String server;
        private Integer recordCount;
        private Integer limit;
        private Integer offset;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @ToString
    @Builder
    public static class ErrorMessage implements Serializable {

        private static final long serialVersionUID = 1952575470352539304L;

        private String developerMessage;
        private String userMessage;
        @Builder.Default
        private String moreInfo = "Email to leonardo.ncintra@outlook.com";
    }
}
