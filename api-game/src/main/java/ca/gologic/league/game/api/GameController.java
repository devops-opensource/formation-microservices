package ca.gologic.league.game.api;

import ca.gologic.league.game.domain.Game;
import ca.gologic.league.game.service.GameService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/game")
@Slf4j
public class GameController {

  @Autowired
  private GameService gameService;

  @GetMapping
  public List<Game> getGames() {
    return gameService.getGames();
  }
}
