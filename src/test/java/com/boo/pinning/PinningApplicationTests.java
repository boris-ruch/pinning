package com.boo.pinning;

import static com.boo.pinning.ClientType.Type.DEFAULT;
import static com.boo.pinning.ClientType.Type.OK_HTTP;
import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PinningApplicationTests {

  @Autowired
  @ClientType(OK_HTTP)
  private RestTemplate restOKHttpTemplate;

  @Autowired
  @ClientType(DEFAULT)
  private RestTemplate restDefaultTemplate;

  @Test
  public void test_pinning_success() {
    // arrange
    String url = "https://sb-hedge-mgmt.horizondev.cloud/swagger-ui.html";
    // act
    ResponseEntity<String> result = restOKHttpTemplate.getForEntity(url, String.class);
    // assert
    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test(expected = Exception.class)
  public void test_notPinnedCertificate_fails() {
    // arrange
    String url = "https://publicobject.com";
    // act
    ResponseEntity<String> result = restOKHttpTemplate.getForEntity(url, String.class);

  }

  @Test
  public void test_notPinnedCertificate_success() {
    // arrange
    String url = "https://publicobject.com";
    // act
    ResponseEntity<String> result = restDefaultTemplate.getForEntity(url, String.class);

  }
}
