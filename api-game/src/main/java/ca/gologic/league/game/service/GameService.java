package ca.gologic.league.game.service;

import ca.gologic.league.game.domain.Game;
import ca.gologic.league.game.domain.Player;
import ca.gologic.league.game.domain.Team;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.contextpropagation.ObservationThreadLocalAccessor;
import io.micrometer.tracing.Tracer;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class GameService {

  @Autowired
  private WebClient teamWebClient;

  @Autowired
  private Tracer tracer;

  @Autowired
  private ObservationRegistry observationRegistry;

  private Mono<List<Team>> teamClient() {
    Observation observation = Observation.start("teamWebClient", observationRegistry);
    return Mono.just(observation).flatMap(span -> {
          observation.scoped(() -> log.info("<ACCEPTANCE_TEST> <TRACE:{}> get teams from team-api",
              this.tracer.currentSpan().context().traceId()));
          return this.teamWebClient.get().retrieve().bodyToMono(new ParameterizedTypeReference<List<Team>>() {});
        })
        .doFinally(signalType -> observation.stop())
        .contextWrite(context -> context.put(ObservationThreadLocalAccessor.KEY, observation));
  }



  public List<Game> getGames() {
    List<Team> teams = teamClient().block();
    assert teams != null;
    Team local = teams.stream().filter(t -> t.getId().equals(1L)).findFirst().orElse(new Team());
    Team visitor = teams.stream().filter(t -> t.getId().equals(2L)).findFirst().orElse(new Team());
    Integer localPoint = getPointForTeam(local);
    Integer visitorPoint = getPointForTeam(visitor);
    Game game1 = getGameInstance(1L, local, visitor, localPoint, visitorPoint, LocalDate.now());
    Game game2 = getGameInstance(2L, local, visitor, 0, 0, LocalDate.now().plusDays(3));

    return List.of(game1, game2);
  }

  private static Game getGameInstance(long id, Team local, Team visitor, Integer localPoint,
      Integer visitorPoint, LocalDate date) {
    return new Game(id,
        date,
        local,
        visitor,
        localPoint,
        visitorPoint);
  }

  private static int getPointForTeam(Team team) {
    return team.getPlayers().stream().mapToInt(Player::getGoal).sum();
  }
}
