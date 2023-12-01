package br.com.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.helpdesk.service.DBservices;

@Configuration
@Profile("dev")
public class DevConfig {

  @Autowired
  private DBservices dBservices;
  /* pegando properties do spring para validar */

  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String value;

  @Bean
  public boolean instanciaDB() {
    if (value.equals("create")) {
      this.dBservices.instanciaDB();
    }
    return false;
  }

}
