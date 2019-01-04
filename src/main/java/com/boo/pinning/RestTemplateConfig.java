package com.boo.pinning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Autowired
  @Qualifier("OKSpringCommonsRestTemplate")
  ClientHttpRequestFactory okHttpRequestFactory;


  @Bean
  @Qualifier("OKSpringCommonsRestTemplate")
  public RestTemplate createOKCustomRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(okHttpRequestFactory);
    return restTemplate;
  }


}
