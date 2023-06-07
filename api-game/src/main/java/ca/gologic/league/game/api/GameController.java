package ca.gologic.league.game.api;

import ca.gologic.league.game.domain.Game;
import ca.gologic.league.game.service.GameService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/game")
@Slf4j
public class GameController {

  @Autowired
  private GameService gameService;

  @Value("${server.instance.id}")
  String instanceId;

  @GetMapping
  public List<Game> getGames(@RequestHeader(name = "X-Request-Game", defaultValue = "filter failed") String extraInfo) {
    log.info("Get all game instance {}", instanceId);
    log.info("Game obtained a header {} with value {}", "X-Request-Game", extraInfo);
    return gameService.getGames();
  }
}
