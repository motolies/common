package io.github.motolies.config;

import io.github.motolies.Interceptor.LoggingRequestInterceptor;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfigurer {

  private static final int CONNECT_TIMEOUT = 30;
  private static final int READ_TIME_OUT = 60;


  public RestTemplate restTemplate() {
    return restTemplate(CONNECT_TIMEOUT, READ_TIME_OUT);
  }

  public RestTemplate restTemplate(boolean logging) {
    return restTemplate(CONNECT_TIMEOUT, READ_TIME_OUT, logging);
  }

  public RestTemplate restTemplate(int connectTimeout, int readTimeOut) {
    return restTemplate(connectTimeout, readTimeOut, null, false);
  }

  public RestTemplate restTemplate(int connectTimeout, int readTimeOut, boolean logging) {
    return restTemplate(connectTimeout, readTimeOut, null, logging);
  }

  public RestTemplate restTemplate(int connectTimeout, int readTimeOut, List<ClientHttpRequestInterceptor> clientHttpRequestInterceptorList, boolean logging) {
    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

    if (logging) {
      if (clientHttpRequestInterceptorList == null) {
        clientHttpRequestInterceptorList = new ArrayList<>();
      }
      clientHttpRequestInterceptorList.add(loggingRequestInterceptor());
    }

    List<ClientHttpRequestInterceptor> interceptors = clientHttpRequestInterceptorList != null
        ? clientHttpRequestInterceptorList
        : Collections.emptyList();

    return new RestTemplateBuilder()
        .requestFactory(() -> new BufferingClientHttpRequestFactory(requestFactory))
        .interceptors(interceptors)
        .setConnectTimeout(Duration.ofSeconds(connectTimeout))
        .setReadTimeout(Duration.ofSeconds(readTimeOut))
        .build();
  }

  @Bean
  public LoggingRequestInterceptor loggingRequestInterceptor() {
    return new LoggingRequestInterceptor();
  }
}