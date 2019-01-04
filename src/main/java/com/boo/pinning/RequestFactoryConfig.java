package com.boo.pinning;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import okhttp3.OkHttpClient;

@Configuration
public class RequestFactoryConfig {

  @Value("${hostname}")
  private String hostname;

  @Value("${pin}")
  private String pin;

  @Bean
  @Qualifier("OKSpringCommonsRestTemplate")
  public ClientHttpRequestFactory createOKCommonsRequestFactory() {
    OkHttpClientFactoryImpl httpClientFactory = new OkHttpClientFactoryImpl();
    OkHttpClient client = httpClientFactory.createBuilder(false, hostname, pin).build();
    return new OkHttp3ClientHttpRequestFactory(client);
  }
}
