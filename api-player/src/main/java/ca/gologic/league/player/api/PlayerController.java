package ca.gologic.league.player.api;

import ca.gologic.league.player.domain.Player;
import ca.gologic.league.player.service.PlayerService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/player")
@Slf4j
public class PlayerController {

  @Autowired
  private PlayerService playerService;

  @Value("${message}")
  private String message;

  @Value("${server.instance.id}")
  String instanceId;

  @GetMapping
  public List<Player> getPlayers(@RequestHeader(name = "X-Request-Game", defaultValue = "filter failed") String extraInfo) {
    log.info("Get all player instance {}", instanceId);
    log.info("Game obtained a header {} with value {}", "X-Request-Game", extraInfo);
    return playerService.getPlayers();
  }

  @PutMapping
  public Player putPlayers(@RequestBody Player player) {
    return playerService.updatePlayer(player);
  }


  @GetMapping
  @RequestMapping("/message")
  public String getPlayerMessage() {
    return message;
  }

}
