
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

    String hostname = "publicobject.com";
     CertificatePinner certificatePinner = new CertificatePinner.Builder()
     .add(hostname, pin)
     .build();

//    CertificatePinner certificatePinner = new CertificatePinner.Builder()
//        .add("publicobject.com", "sha256/afwiKY3RxoMmLkuRW1l7QsPZTJPwDS2pdDROQjXw8ig=")
//        .add("publicobject.com", "sha256/klO23nT2ehFDXCfx3eHTDRESMz3asj1muO+4aIdjiuY=")
//        .add("publicobject.com", "sha256/grX4Ta9HpZx6tSHkmCrvpApTQGo67CYDnvprLg5yRME=")
//        .add("publicobject.com", "sha256/lCppFqbkrlJ3EcVFAkeip0+44VaoJUymbnOaEUk7tEU=")
//        .build();

    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    ConnectionPool okHttpConnectionPool = new ConnectionPool(50, 30, TimeUnit.SECONDS);
    builder.connectionPool(okHttpConnectionPool);
    builder.connectTimeout(20, TimeUnit.SECONDS);
    builder.certificatePinner(certificatePinner);
    builder.retryOnConnectionFailure(false);
    return builder;
  }

}
