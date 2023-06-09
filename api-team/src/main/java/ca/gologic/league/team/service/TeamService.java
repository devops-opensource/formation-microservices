package ca.gologic.league.team.service;

import ca.gologic.league.team.domain.Player;
import ca.gologic.league.team.domain.Team;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.contextpropagation.ObservationThreadLocalAccessor;
import io.micrometer.tracing.Tracer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TeamService {

  @Autowired
  private WebClient playerWebClient;

  @Autowired
  private Tracer tracer;

  @Autowired
  private ObservationRegistry observationRegistry;

  private Mono<List<Player>> playerClient() {
    Observation observation = Observation.start("playerWebClient", observationRegistry);
    return Mono.just(observation).flatMap(span -> {
          observation.scoped(() -> log.info("<ACCEPTANCE_TEST> <TRACE:{}> get players from player-api",
              this.tracer.currentSpan().context().traceId()));
          return this.playerWebClient.get().retrieve().bodyToMono(new ParameterizedTypeReference<List<Player>>() {});
        })
        .doFinally(signalType -> observation.stop())
        .contextWrite(context -> context.put(ObservationThreadLocalAccessor.KEY, observation));
  }

  @CircuitBreaker(name = "TeamApiCircuitBreaker", fallbackMethod = "fallback")
  public List<Team> getTeams() {
    List<Player> players = playerClient().block();

    Team team1 = new Team(1L, "Team Gologic", players.stream().filter(p -> p.getId().equals(1L) || p.getId().equals(2L)).collect(Collectors.toList()));
    Team team2 = new Team(2L, "Team Desjardins", players.stream().filter(p -> p.getId().equals(3L) || p.getId().equals(4L)).collect(Collectors.toList()));

    return List.of(team1, team2);
  }

  private List<Team> fallback(Throwable t) {
    log.info("Circuit breaker OPEN");
    return List.of(new Team(-1L, "CRICUIT BREAKER", new ArrayList<>()));
  }
}
