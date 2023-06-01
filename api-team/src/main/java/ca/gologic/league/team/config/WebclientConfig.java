package ca.gologic.league.team.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfig {
  @Bean
  WebClient playerWebClient(WebClient.Builder builder, @Value("${client.player.uri}") String url) {
    return builder.baseUrl(url).build();
  }
}
