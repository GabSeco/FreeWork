package com.freework;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiGatewayRequest {

    private Map<String, String> headers;
    private Map<String, String> body;
    private Map<String, String> queryParams;
}