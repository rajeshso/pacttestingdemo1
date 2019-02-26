package com.n2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.n2.*"})
@EnableCaching
@EnableScheduling
public class ProviderMain {

  public static void main(String[] args) {
    SpringApplication.run(ProviderMain.class, args);
  }
}
