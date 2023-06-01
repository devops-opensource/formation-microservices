package ca.gologic.league.player.api;

import ca.gologic.league.player.domain.Player;
import ca.gologic.league.player.service.PlayerService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping
  public List<Player> getPlayers(HttpServletRequest request) {
    log.info("Get all player");
    log.info("traceparent: {}", request.getHeader("traceparent"));
    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = request.getHeader(key);
      log.info("header: {} value: {}", key, value);
    }
    return playerService.getPlayers();
  }

  @GetMapping
  @RequestMapping("/message")
  public String getPlayerMessage() {
    return message;
  }

}
