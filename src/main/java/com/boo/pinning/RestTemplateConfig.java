package com.boo.pinning;

import static com.boo.pinning.ClientType.Type.DEFAULT;
import static com.boo.pinning.ClientType.Type.OK_HTTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Autowired
  @ClientType(OK_HTTP)
  ClientHttpRequestFactory okHttpRequestFactory;

  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  @Bean
  @ClientType(OK_HTTP)
  public RestTemplate createOKCustomRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(okHttpRequestFactory);
    return restTemplate;
  }

  @Bean
  @ClientType(DEFAULT)
  public RestTemplate createDefaultRestTemplate() {
    return restTemplateBuilder.build();
  }

}
