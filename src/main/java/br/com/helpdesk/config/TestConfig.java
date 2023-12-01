package br.com.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.helpdesk.service.DBservices;

@Configuration
@Profile("test")
public class TestConfig {

  @Autowired
  private DBservices dBservices;

  @Bean
  public void instanciaDB(){
    this.dBservices.instanciaDB();
  }

}
