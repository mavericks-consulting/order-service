package com.mavericks.orderservice.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

  private RestTemplate rest;
  private HttpHeaders headers;

  public RestClient() {
    this.rest = new RestTemplate();
    this.headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("Accept", "*/*");
  }

  public ResponseEntity<String> get(String server, String port, String uri) {
    HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
    return rest.exchange("http://" + server + ":" + port + uri, HttpMethod.GET, requestEntity, String.class);
  }
}