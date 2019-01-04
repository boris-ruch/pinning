
package com.boo.pinning;

import java.util.concurrent.TimeUnit;

import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;

import okhttp3.CertificatePinner;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

public class OkHttpClientFactoryImpl implements OkHttpClientFactory {

  private String hostname;

  private String pin;

  public OkHttpClient.Builder createBuilder(boolean disableSslValidation, String hostname, String pin) {
    this.hostname = hostname;
    this.pin = pin;
    return createBuilder(disableSslValidation);
  }

  @Override
  public OkHttpClient.Builder createBuilder(boolean disableSslValidation) {

    CertificatePinner certificatePinner = new CertificatePinner.Builder().add(hostname, pin).build();
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    ConnectionPool okHttpConnectionPool = new ConnectionPool(50, 30, TimeUnit.SECONDS);
    builder.connectionPool(okHttpConnectionPool);
    builder.connectTimeout(20, TimeUnit.SECONDS);
    builder.certificatePinner(certificatePinner);
    builder.retryOnConnectionFailure(false);
    return builder;
  }

}
