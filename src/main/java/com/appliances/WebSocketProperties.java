package com.appliances;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("app.websocket")
public class WebSocketProperties {

    private String applicationPrefix = "/topic";

    private String clientDestinationPrefix = "/topic";

    private String endpoint = "/websocket";

    private String[] allowedOrigins = new String[0];
}
