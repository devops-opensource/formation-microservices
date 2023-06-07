package ca.gologic.league.game.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ServerInstanceConfiguration {

  @Value("${client.team.alias}")
  private String alias;

  @Value("${client.team.uri-instance-1}")
  private String teamServiceUri1;

  @Value("${client.team.uri-instance-2}")
  private String teamServiceUri2;

  @Bean
  ServiceInstanceListSupplier serviceInstanceListSupplier() {
    return new InstanceSupplier(alias, teamServiceUri1, teamServiceUri2);
  }
}
