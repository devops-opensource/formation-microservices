package ca.gologic.league.team.api;

import ca.gologic.league.team.domain.Team;
import ca.gologic.league.team.service.TeamService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/team")
@Slf4j
public class TeamController {

  @Autowired
  private TeamService teamService;

  @Value("${server.instance.id}")
  String instanceId;

  @GetMapping
  public List<Team> getPlayers(@RequestHeader(name = "X-Request-Game", defaultValue = "filter failed") String extraInfo) {
    log.info("Get all team instance {}", instanceId);
    log.info("Game obtained a header {} with value {}", "X-Request-Game", extraInfo);

    return null;
  }
}
