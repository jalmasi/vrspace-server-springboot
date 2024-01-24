package org.vrspace.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackages = { "org.vrspace.server", "org.vrspace.example" })
public class ExampleServerApplication extends WebMvcConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(ExampleServerApplication.class, args);
  }

  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/index").setViewName("index");
  }
}
