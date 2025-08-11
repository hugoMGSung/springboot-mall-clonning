package com.hugo83.webmall.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
  @Override
  public void customize(TomcatServletWebServerFactory factory) {
    factory.addConnectorCustomizers(connector -> connector.setMaxPartCount(50)); // -1: 무제한
  }
}