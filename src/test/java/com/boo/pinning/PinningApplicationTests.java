package com.boo.pinning;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PinningApplicationTests {

  @Autowired
  @Qualifier("OKSpringCommonsRestTemplate")
  private RestTemplate restTemplate;

  @Test
  public void test_pinning_success() {
    // arrange
    String url = "https://sb-hedge-mgmt.horizondev.cloud/swagger-ui.html";
    // act
    ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
    // assert
    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test(expected = Exception.class)
  public void test_notPinnedCertificate_fails() {
    // arrange
    String url = "https://publicobject.com";
    // act
    ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

  }
}
