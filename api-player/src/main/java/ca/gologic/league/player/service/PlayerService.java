package ca.gologic.league.player.service;

import ca.gologic.league.player.domain.Player;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class PlayerService {

  public List<Player> getPlayers() {
    return List.of(new Player(1L, "Sidney Crosby", 32, 0),
        new Player(2L, "Wayne Gretzky", 21, 0),
        new Player(3L, "Alexander Ovechkin", 33, 0),
        new Player(4L, "Connor McDavid", 33, 0));
  }
}
